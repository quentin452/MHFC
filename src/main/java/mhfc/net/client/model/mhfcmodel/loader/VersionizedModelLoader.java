package mhfc.net.client.model.mhfcmodel.loader;

import java.io.BufferedInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.Map;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

import mhfc.net.MHFCMain;
import mhfc.net.client.model.mhfcmodel.data.IRawData;
import mhfc.net.client.model.mhfcmodel.data.RawDataV1;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelFormatException;
import sun.nio.cs.ArrayDecoder;

public abstract class VersionizedModelLoader {
	public static final long magic = 0x4d484643204d444cL; // = MHMD
	private static Map<Integer, VersionizedModelLoader> registeredLoaders;
	private static CharsetDecoder utf8Decoder = Charset.forName("UTF-8")
			.newDecoder().onMalformedInput(CodingErrorAction.REPORT)
			.onUnmappableCharacter(CodingErrorAction.REPORT);

	static {
		registerLoader(1, LoaderVersion1.instance);
	}
	/**
	 * I don't think we ever gonna reach this number but version will be
	 * interpreted as unsigned here.
	 *
	 * @param version
	 *            the version to register the loader for
	 * @param vml
	 *            the loader to register
	 */
	public static boolean registerLoader(int version, VersionizedModelLoader vml) {
		return registeredLoaders.put(version, vml) != null;
	}

	public static IRawData loadVersionized(ResourceLocation resLocation) {
		try (DataInputStream dis = new DataInputStream(new BufferedInputStream(
				Minecraft.getMinecraft().getResourceManager()
						.getResource(resLocation).getInputStream()))) {
			long foundMagic = dis.readLong();
			if (foundMagic != magic) {
				throw new ModelFormatException(String.format(
						"Wrong magic number. Found %x, expected %x.",
						foundMagic, magic));
			}

			long modelHash = dis.readLong();
			String artist = readStringStatic(dis);

			int version = dis.readInt();
			VersionizedModelLoader loader = registeredLoaders.get(version);
			if (loader == null)
				throw new ModelFormatException("Unrecognized model version.");

			IRawData data = loader.loadFromInputStream(version, dis);
			MHFCMain.logger
					.debug("Successfully loaded model %s, version %d from artist %s. (Modelhash: %x)",
							resLocation, version, artist, modelHash);
			return data;

		} catch (NullPointerException npe) {
			throw new ModelFormatException("File can't be null.", npe);
		} catch (EOFException eofe) {
			throw new ModelFormatException(String.format(
					"Unexpected end of file.", resLocation), eofe);
		} catch (IOException ioe) {
			throw new ModelFormatException(String.format(
					"Can't read from resource given (%s).", resLocation), ioe);
		} catch (ModelFormatException mfe) {
			throw new ModelFormatException(
					String.format(
							"A model format exception occured when reading from file %s",
							resLocation), mfe);
		}
	}

	private static String readStringStatic(DataInput dis) throws IOException {
		// Read bytes
		byte[] buffer = new byte[64];
		int currLength = 64;
		int offset = 0;
		for (byte currByte; (currByte = dis.readByte()) != 0;) {
			if (offset == currLength) {
				currLength += 64;
				buffer = Arrays.copyOf(buffer, currLength);
			}
			buffer[offset++] = currByte;
		}
		// Return empty string
		if (offset == 0)
			return "";
		// Could use String(byte[], Charset) here but want some control over
		// fail-actions
		// Prepare
		utf8Decoder.reset();
		int strLen = 0;
		int maxLen = (int) Math.ceil(offset * utf8Decoder.maxCharsPerByte());
		char[] target = new char[maxLen];
		if (utf8Decoder instanceof ArrayDecoder) {
			// If array possible
			strLen = ((ArrayDecoder) utf8Decoder).decode(buffer, 0, offset,
					target);
		} else {
			// Wrap in buffer
			ByteBuffer sourceBuff = ByteBuffer.wrap(buffer, 0, offset);
			CharBuffer targetBuff = CharBuffer.wrap(target);
			// Standard decoder procedure
			try {
				CoderResult cr = utf8Decoder.decode(sourceBuff, targetBuff,
						true);
				if (!cr.isUnderflow())
					cr.throwException();
				cr = utf8Decoder.flush(targetBuff);
				if (!cr.isUnderflow())
					cr.throwException();
			} catch (CharacterCodingException x) {} // Shouldn't happen
			strLen = targetBuff.position();
		}
		return new String(Arrays.copyOf(target, strLen));
	}
	/**
	 * Actually loads the inputstream into the {@link RawDataV1} format.
	 *
	 * @param version
	 *            the version of the file if one handler is registered for
	 *            multiple versions
	 * @param is
	 *            the input stream to load from
	 * @return a fully loaded {@link RawDataV1}
	 * @throws IOException
	 *             when either the file is too short or another IOExceptio
	 *             occurs
	 */
	public abstract IRawData loadFromInputStream(int version, DataInput is)
			throws IOException;

	/**
	 * Helper method for extending classes to read a null-terminated String in
	 * the BMP-unicode plane. THIS MEANS ONLY UNICODE U+0001 - U+FFFF are
	 * supported.<br>
	 * All characters from U+10000 must be present as surrogate characters
	 * ranging from U+D800 - U+DFFF.<br>
	 * Refer to the Charset.forName("UTF-8")<br>
	 * The String will be terminated by the first 0x00 that is encountered.
	 *
	 * @see
	 * @param dis
	 *            the {@link DataInput} to read from
	 * @return the read {@link String}
	 * @throws EOFException
	 *             when the datainput ends before a '\x00\x00' is read
	 * @throws IOException
	 *             when some IOException occurs in the given {@link DataInput}
	 */
	@SuppressWarnings("static-method")
	protected final String readString(DataInput dis) throws EOFException,
			IOException {
		return VersionizedModelLoader.readStringStatic(dis);
	}

	/**
	 * Reads a {@link Vector2f} from the datainput
	 *
	 * @param dis
	 *            the {@link DataInput} to read from
	 * @return the constructed {@link Vector3f}
	 * @throws EOFException
	 *             when the data ends before 3 floats are read
	 * @throws IOException
	 *             when some IOException occurs in the given {@link DataInput}
	 */
	@SuppressWarnings("static-method")
	protected final Vector2f readVector2f(DataInput dis) throws EOFException,
			IOException {
		float x = dis.readFloat();
		float y = dis.readFloat();
		return new Vector2f(x, y);
	}

	/**
	 * Reads a {@link Vector3f} from the datainput
	 *
	 * @param dis
	 *            the {@link DataInput} to read from
	 * @return the constructed {@link Vector3f}
	 * @throws EOFException
	 *             when the data ends before 3 floats are read
	 * @throws IOException
	 *             when some IOException occurs in the given {@link DataInput}
	 */
	@SuppressWarnings("static-method")
	protected final Vector3f readVector3f(DataInput dis) throws EOFException,
			IOException {
		float x = dis.readFloat();
		float y = dis.readFloat();
		float z = dis.readFloat();
		return new Vector3f(x, y, z);
	}

	/**
	 * Reads a {@link Matrix4f} from the given {@link DataInput}. A matrix4 is
	 * basically 4 {@link Vector4f} so 16 floats will be read.<br>
	 * When isAffine is specified the matrix's last <b>row</b> will be assumed
	 * to be (0, 0, 0, 1). In this case only 12 floats will be read.<br>
	 * When rowsFirst is set the read Vectors will be taken as the rows of the
	 * matrix else as the matrix's columns.
	 *
	 * @param dis
	 *            the {@link DataInput} to read from
	 * @param isAffine
	 *            whether the Matrix to be read is affine
	 * @param rowsFirst
	 *            whether to interpret the read {@link Vector4f} as the rows or
	 *            columns of the matrix
	 * @return the newly constructed matrix
	 * @throws EOFException
	 *             when the data ends before 12/16 floats are read
	 * @throws IOException
	 *             when some IOException occurs in the given {@link DataInput}
	 */
	@SuppressWarnings("static-method")
	protected final Matrix4f readMatrix(DataInput dis, boolean isAffine,
			boolean rowsFirst) throws EOFException, IOException {
		float v0 = dis.readFloat();
		float v1 = dis.readFloat();
		float v2 = dis.readFloat();
		float v3 = dis.readFloat();
		float v4 = dis.readFloat();
		float v5 = dis.readFloat();
		float v6 = dis.readFloat();
		float v7 = dis.readFloat();
		float v8 = dis.readFloat();
		float v9 = dis.readFloat();
		float v10 = dis.readFloat();
		float v11 = dis.readFloat();
		if (isAffine) {
			if (rowsFirst) {
				// Matrix is rowsFirst
				return new Matrix4f(v0, v1, v2, v3, v4, v5, v6, v7, v8, v9,
						v10, v11, 0.0f, 0.0f, 0.0f, 1.0f);
			}
			return new Matrix4f(v0, v3, v6, v9, v1, v4, v7, v10, v2, v5, v8,
					v11, 0.0f, 0.0f, 0.0f, 1.0f);
		}
		float v12 = dis.readFloat();
		float v13 = dis.readFloat();
		float v14 = dis.readFloat();
		float v15 = dis.readFloat();
		if (rowsFirst) {
			// Matrix is rowsFirst
			return new Matrix4f(v0, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10,
					v11, v12, v13, v14, v15);
		}
		return new Matrix4f(v0, v4, v8, v12, v1, v5, v9, v13, v2, v6, v10, v14,
				v3, v7, v11, v15);
	}

	/**
	 * Reads a {@link Quat4f} from the given {@link InputStream}.
	 *
	 * @param dis
	 *            the {@link DataInput} to read from
	 * @throws EOFException
	 *             when the data ends before 4 floats are read
	 * @throws IOException
	 *             when some IOException occurs in the given {@link DataInput}
	 */
	@SuppressWarnings("static-method")
	protected final Quat4f readQuat(DataInput dis) throws EOFException,
			IOException {
		float x = dis.readFloat();
		float y = dis.readFloat();
		float z = dis.readFloat();
		float w = dis.readFloat();
		return new Quat4f(x, y, z, w);
	}
}
