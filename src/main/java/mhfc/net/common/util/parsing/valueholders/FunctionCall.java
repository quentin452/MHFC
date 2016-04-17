package mhfc.net.common.util.parsing.valueholders;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Supplier;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import mhfc.net.common.util.parsing.Holder;
import mhfc.net.common.util.parsing.IValueHolder;
import mhfc.net.common.util.reflection.MethodHelper;
import mhfc.net.common.util.reflection.OverloadedMethod;

public class FunctionCall implements IValueHolder {
	private static interface ICall {
		boolean isTypeFinal();

		Class<?> getType();

		Holder call(Object instance, Arguments args);
	}

	private static class MethodProxy implements ICall {
		private final Method method;
		private final Supplier<RuntimeException> error;

		public MethodProxy(Method method) {
			assert method.getParameterTypes().length == 1;
			assert method.getParameterTypes()[0].isAssignableFrom(Arguments.class);
			if (!Holder.class.isAssignableFrom(method.getReturnType())) {
				error = () -> new IllegalArgumentException("__call__ must return Holder");
			} else {
				error = null;
			}
			this.method = method;
		}

		@Override
		public Class<?> getType() {
			return IValueHolder.EMPTY_CLASS;
		}

		@Override
		public boolean isTypeFinal() {
			return error != null;
		}

		@Override
		public Holder call(Object instance, Arguments args) {
			if (error != null) {
				return Holder.failedComputation(error.get());
			}
			try {
				return Holder.class.cast(method.invoke(instance, args));
			} catch (InvocationTargetException | IllegalAccessException ite) {
				throw new RuntimeException(ite);
			}
		}
	}

	private static class NoCallFound implements ICall {
		private final Class<?> clazz;

		public NoCallFound(Class<?> clazz) {
			this.clazz = clazz;
		}

		@Override
		public Holder call(Object instance, Arguments args) {
			return Holder.failedComputation(new NoSuchMethodException(clazz.getName() + ".__call__ hasn't been found"));
		}

		@Override
		public Class<?> getType() {
			return IValueHolder.EMPTY_CLASS;
		}

		@Override
		public boolean isTypeFinal() {
			return true;
		}
	}

	private static LoadingCache<Class<?>, ICall> callCache;

	static {
		callCache = CacheBuilder.newBuilder().maximumSize(1000).build(new CacheLoader<Class<?>, ICall>() {
			@Override
			public ICall load(Class<?> key) {
				return computeCall(key);
			}
		});
	}

	private static ICall resolveCall(Class<?> clazz) {
		return callCache.getUnchecked(clazz);
	}

	private static ICall computeCall(Class<?> clazz) {
		Optional<OverloadedMethod> specialMethod = MethodHelper.findMatching(clazz, "__call__");
		Optional<Method> call = specialMethod.flatMap(s -> s.disambiguate(Arguments.class));
		if (call.isPresent()) {
			return new MethodProxy(call.get());
		}
		return new NoCallFound(clazz);
	}

	private static class BoundFunctionCall implements IValueHolder {
		private ICall resolvedCall;
		private IValueHolder holder;
		private Arguments args;

		public BoundFunctionCall(IValueHolder holder, Arguments args) {
			assert holder.isTypeFinal();
			this.resolvedCall = resolveCall(holder.getType());
			this.holder = holder;
			this.args = args;
		}

		@Override
		public Class<?> getType() {
			return resolvedCall.getType();
		}

		@Override
		public boolean isTypeFinal() {
			return resolvedCall.isTypeFinal();
		}

		@Override
		public Holder snapshot() {
			Holder instance = holder.snapshot();
			return resolvedCall.call(instance.boxed(), args);
		}
	}

	private IValueHolder holder;
	private Arguments args;

	private FunctionCall(IValueHolder holder, Arguments args) {
		this.holder = holder;
		this.args = args;
	}

	@Override
	public Holder snapshot() {
		Holder instance = holder.snapshot();
		return resolveCall(instance.getType()).call(instance.boxed(), args);
	}

	@Override
	public Class<?> getType() {
		return IValueHolder.EMPTY_CLASS;
	}

	public static IValueHolder makeFunctionCall(IValueHolder callee, IValueHolder... arguments) {
		Arguments args = new Arguments(arguments);
		if (callee.isTypeFinal()) {
			return new BoundFunctionCall(callee, args);
		}
		return new FunctionCall(callee, args);
	}
}
