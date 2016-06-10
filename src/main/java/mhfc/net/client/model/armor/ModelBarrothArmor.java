package mhfc.net.client.model.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBarrothArmor extends ModelBiped
{
  //fields
    ModelRenderer heada;
    ModelRenderer headb;
    ModelRenderer headc;
    ModelRenderer headd;
    ModelRenderer heade;
    ModelRenderer headf;
    ModelRenderer headg;
    ModelRenderer headh;
    ModelRenderer headi;
    ModelRenderer headj;
    ModelRenderer headk;
    ModelRenderer headl;
    ModelRenderer headm;
    ModelRenderer headn;
    ModelRenderer heado;
    ModelRenderer headp;
    ModelRenderer headq;
    ModelRenderer ba;
    ModelRenderer bb;
    ModelRenderer bc;
    ModelRenderer bd;
    ModelRenderer be;
    ModelRenderer bf;
    ModelRenderer bg;
    ModelRenderer bh;
    ModelRenderer ra;
    ModelRenderer rb;
    ModelRenderer rc;
    ModelRenderer rd;
    ModelRenderer re;
    ModelRenderer rf;
    ModelRenderer rg;
    ModelRenderer rh;
    ModelRenderer ri;
    ModelRenderer rj;
    ModelRenderer rk;
    ModelRenderer la;
    ModelRenderer lb;
    ModelRenderer lc;
    ModelRenderer ld;
    ModelRenderer le;
    ModelRenderer lf;
    ModelRenderer lg;
    ModelRenderer lh;
    ModelRenderer li;
    ModelRenderer lj;
    ModelRenderer lk;
    ModelRenderer ll;
    ModelRenderer lfa;
    ModelRenderer lfb;
    ModelRenderer rfa;
    ModelRenderer rfb;
  
  public ModelBarrothArmor(float f)
  {
	  super(f, 0, 128, 128);
	  textureWidth = 128;
	  textureHeight = 128;
      heada = new ModelRenderer(this, 0, 36);
      heada.addBox(-1F, -8F, -5F, 2, 5, 2);
      heada.setRotationPoint(0F, 0F, 0F);
      heada.setTextureSize(128, 128);
      heada.mirror = true;
      setRotation(heada, 0F, 0F, 0F);
      headb = new ModelRenderer(this, 0, 44);
      headb.addBox(-1.5F, -13F, -5.5F, 3, 6, 3);
      headb.setRotationPoint(0F, 0F, 0F);
      headb.setTextureSize(128, 128);
      headb.mirror = true;
      setRotation(headb, 0F, 0F, 0F);
      headc = new ModelRenderer(this, 0, 54);
      headc.addBox(-2.5F, -12.5F, -5F, 5, 4, 2);
      headc.setRotationPoint(0F, 0F, 0F);
      headc.setTextureSize(128, 128);
      headc.mirror = true;
      setRotation(headc, 0F, 0F, 0F);
      headd = new ModelRenderer(this, 0, 61);
      headd.addBox(-4.5F, -8F, -4.5F, 9, 3, 1);
      headd.setRotationPoint(0F, 0F, 0F);
      headd.setTextureSize(128, 128);
      headd.mirror = true;
      setRotation(headd, 0F, 0F, 0F);
      heade = new ModelRenderer(this, 0, 66);
      heade.addBox(-5F, -4.2F, -5F, 4, 4, 1);
      heade.setRotationPoint(0F, 0F, 0F);
      heade.setTextureSize(128, 128);
      heade.mirror = true;
      setRotation(heade, 0F, 0F, 0F);
      headf = new ModelRenderer(this, 11, 66);
      headf.addBox(1F, -4.2F, -5F, 4, 4, 1);
      headf.setRotationPoint(0F, 0F, 0F);
      headf.setTextureSize(128, 128);
      headf.mirror = true;
      setRotation(headf, 0F, 0F, 0F);
      headg = new ModelRenderer(this, 0, 72);
      headg.addBox(-1F, -3.2F, -5.4F, 2, 3, 2);
      headg.setRotationPoint(0F, 0F, 0F);
      headg.setTextureSize(128, 128);
      headg.mirror = true;
      setRotation(headg, 0F, 0F, 0F);
      headh = new ModelRenderer(this, 0, 78);
      headh.addBox(3.4F, -3.5F, -4.5F, 1, 3, 9);
      headh.setRotationPoint(0F, 0F, 0F);
      headh.setTextureSize(128, 128);
      headh.mirror = true;
      setRotation(headh, 0F, 0F, 0F);
      headi = new ModelRenderer(this, 0, 78);
      headi.addBox(-4.5F, -3.5F, -4.5F, 1, 3, 9);
      headi.setRotationPoint(0F, 0F, 0F);
      headi.setTextureSize(128, 128);
      headi.mirror = true;
      setRotation(headi, 0F, 0F, 0F);
      headj = new ModelRenderer(this, 0, 91);
      headj.addBox(-5F, -4F, 4F, 10, 4, 1);
      headj.setRotationPoint(0F, 0F, 0F);
      headj.setTextureSize(128, 128);
      headj.mirror = true;
      setRotation(headj, 0F, 0F, 0F);
      headk = new ModelRenderer(this, 0, 97);
      headk.addBox(-4.5F, -13F, -2F, 2, 6, 3);
      headk.setRotationPoint(0F, 0F, 0F);
      headk.setTextureSize(128, 128);
      headk.mirror = true;
      setRotation(headk, 0F, 0F, 0F);
      headl = new ModelRenderer(this, 0, 97);
      headl.addBox(2.5F, -13F, -2F, 2, 6, 3);
      headl.setRotationPoint(0F, 0F, 0F);
      headl.setTextureSize(128, 128);
      headl.mirror = true;
      setRotation(headl, 0F, 0F, 0F);
      headm = new ModelRenderer(this, 0, 107);
      headm.addBox(-4.4F, -7.9F, -4F, 1, 3, 9);
      headm.setRotationPoint(0F, 0F, 0F);
      headm.setTextureSize(128, 128);
      headm.mirror = true;
      setRotation(headm, 0F, 0F, 0F);
      headn = new ModelRenderer(this, 0, 107);
      headn.addBox(3.4F, -7.9F, -4F, 1, 3, 9);
      headn.setRotationPoint(0F, 0F, 0F);
      headn.setTextureSize(128, 128);
      headn.mirror = true;
      setRotation(headn, 0F, 0F, 0F);
      heado = new ModelRenderer(this, 9, 36);
      heado.addBox(-3.5F, -7.9F, 4F, 7, 3, 1);
      heado.setRotationPoint(0F, 0F, 0F);
      heado.setTextureSize(128, 128);
      heado.mirror = true;
      setRotation(heado, 0F, 0F, 0F);
      headp = new ModelRenderer(this, 21, 109);
      headp.addBox(-4F, -9F, -4F, 8, 1, 9);
      headp.setRotationPoint(0F, 0F, 0F);
      headp.setTextureSize(128, 128);
      headp.mirror = true;
      setRotation(headp, 0F, 0F, 0F);
      headq = new ModelRenderer(this, 24, 96);
      headq.addBox(-1.5F, -12F, -2F, 3, 3, 9);
      headq.setRotationPoint(0F, 0F, 0F);
      headq.setTextureSize(128, 128);
      headq.mirror = true;
      setRotation(headq, 0F, 0F, 0F);
      ba = new ModelRenderer(this, 27, 36);
      ba.addBox(-4.1F, 0F, -4F, 2, 10, 1);
      ba.setRotationPoint(0F, 0F, 0F);
      ba.setTextureSize(128, 128);
      ba.mirror = true;
      setRotation(ba, 0F, 0F, 0F);
      bb = new ModelRenderer(this, 16, 41);
      bb.addBox(-2F, 1F, -4.2F, 1, 9, 1);
      bb.setRotationPoint(0F, 0F, 0F);
      bb.setTextureSize(128, 128);
      bb.mirror = true;
      setRotation(bb, 0F, 0F, 0F);
      bc = new ModelRenderer(this, 21, 49);
      bc.addBox(-1F, 0F, -5F, 2, 11, 2);
      bc.setRotationPoint(0F, 0F, 0F);
      bc.setTextureSize(128, 128);
      bc.mirror = true;
      setRotation(bc, 0F, 0F, 0F);
      bd = new ModelRenderer(this, 16, 41);
      bd.addBox(1F, 1F, -4F, 1, 9, 1);
      bd.setRotationPoint(0F, 0F, 0F);
      bd.setTextureSize(128, 128);
      bd.mirror = true;
      setRotation(bd, 0F, 0F, 0F);
      be = new ModelRenderer(this, 27, 36);
      be.addBox(2.1F, 0F, -4F, 2, 10, 1);
      be.setRotationPoint(0F, 0F, 0F);
      be.setTextureSize(128, 128);
      be.mirror = true;
      setRotation(be, 0F, 0F, 0F);
      bf = new ModelRenderer(this, 22, 63);
      bf.addBox(-4F, 1F, -3F, 8, 8, 1);
      bf.setRotationPoint(0F, 0F, 0F);
      bf.setTextureSize(128, 128);
      bf.mirror = true;
      setRotation(bf, 0F, 0F, 0F);
      bg = new ModelRenderer(this, 22, 73);
      bg.addBox(-4F, 9F, -3.5F, 8, 2, 2);
      bg.setRotationPoint(0F, 0F, 0F);
      bg.setTextureSize(128, 128);
      bg.mirror = true;
      setRotation(bg, 0F, 0F, 0F);
      bh = new ModelRenderer(this, 23, 78);
      bh.addBox(-4F, 0F, 1.6F, 8, 12, 2);
      bh.setRotationPoint(0F, 0F, 0F);
      bh.setTextureSize(128, 128);
      bh.mirror = true;
      setRotation(bh, 0F, 0F, 0F);
      ra = new ModelRenderer(this, 34, 36);
      ra.addBox(-4F, -3F, -2.5F, 5, 3, 5);
      ra.setRotationPoint(0F,0F,0F);
      ra.setTextureSize(128, 128);
      ra.mirror = true;
      setRotation(ra, 0F, 0F, 0F);
      rb = new ModelRenderer(this, 30, 49);
      rb.addBox(-3F, -8F, -3F, 2, 7, 2);
      rb.setRotationPoint(0F,0F,0F);
      rb.setTextureSize(128, 128);
      rb.mirror = true;
      setRotation(rb, 0F, 0F, 0F);
      rc = new ModelRenderer(this, 30, 49);
      rc.addBox(-2F, -8F, 1.5F, 2, 7, 2);
      rc.setRotationPoint(0F,0F,0F);
      rc.setTextureSize(128, 128);
      rc.mirror = true;
      setRotation(rc, 0F, 0F, 0F);
      rd = new ModelRenderer(this, 39, 49);
      rd.addBox(-2.5F, -7F, -1F, 2, 5, 2);
      rd.setRotationPoint(0F,0F,0F);
      rd.setTextureSize(128, 128);
      rd.mirror = true;
      setRotation(rd, 0F, 0F, -0.2617994F);
      re = new ModelRenderer(this, 48, 50);
      re.addBox(-4.2F, -6F, -0.5F, 1, 5, 1);
      re.setRotationPoint(0F,0F,0F);
      re.setTextureSize(128, 128);
      re.mirror = true;
      setRotation(re, 0F, 0F, 0F);
      rf = new ModelRenderer(this, 41, 58);
      rf.addBox(-1F, -7F, -3F, 1, 6, 2);
      rf.setRotationPoint(0F,0F,0F);
      rf.setTextureSize(128, 128);
      rf.mirror = true;
      setRotation(rf, 0F, 0F, 0F);
      rg = new ModelRenderer(this, 41, 58);
      rg.addBox(-3F, -7F, 1.5F, 1, 6, 2);
      rg.setRotationPoint(0F,0F,0F);
      rg.setTextureSize(128, 128);
      rg.mirror = true;
      setRotation(rg, 0F, 0F, 0F);
      rh = new ModelRenderer(this, 44, 69);
      rh.addBox(-3F, 0F, -2.3F, 4, 3, 1);
      rh.setRotationPoint(0F,0F,0F);
      rh.setTextureSize(128, 128);
      rh.mirror = true;
      setRotation(rh, 0F, 0F, 0F);
      ri = new ModelRenderer(this, 44, 75);
      ri.addBox(-4F, 0F, -2F, 1, 3, 4);
      ri.setRotationPoint(0F,0F,0F);
      ri.setTextureSize(128, 128);
      ri.mirror = true;
      setRotation(ri, 0F, 0F, 0F);
      rj = new ModelRenderer(this, 44, 69);
      rj.addBox(-3F, 0F, 1.3F, 4, 3, 1);
      rj.setRotationPoint(0F,0F,0F);
      rj.setTextureSize(128, 128);
      rj.mirror = true;
      setRotation(rj, 0F, 0F, 0F);
      rk = new ModelRenderer(this, 48, 58);
      rk.addBox(-4.5F, -3F, -1F, 1, 2, 2);
      rk.setRotationPoint(0F,0F,0F);
      rk.setTextureSize(128, 128);
      rk.mirror = true;
      setRotation(rk, 0F, 0F, -0.7853982F);
      la = new ModelRenderer(this, 34, 36);
      la.addBox(-1F, -3F, -2.5F, 5, 3, 5);
      la.setRotationPoint(0f,0f,0f);
      la.setTextureSize(128, 128);
      la.mirror = true;
      setRotation(la, 0F, 0F, 0F);
      lb = new ModelRenderer(this, 44, 84);
      lb.addBox(0.8F, -7.5F, -3F, 2, 7, 1);
      lb.setRotationPoint(0f,0f,0f);
      lb.setTextureSize(128, 128);
      lb.mirror = true;
      setRotation(lb, 0F, 0F, 0F);
      lc = new ModelRenderer(this, 51, 85);
      lc.addBox(-0.2F, -6F, -3F, 1, 5, 2);
      lc.setRotationPoint(0f,0f,0f);
      lc.setTextureSize(128, 128);
      lc.mirror = true;
      setRotation(lc, 0F, 0F, 0F);
      ld = new ModelRenderer(this, 51, 85);
      ld.addBox(2.8F, -6F, -3F, 1, 5, 2);
      ld.setRotationPoint(0f,0f,0f);
      ld.setTextureSize(128, 128);
      ld.mirror = true;
      setRotation(ld, 0F, 0F, 0F);
      le = new ModelRenderer(this, 39, 49);
      le.addBox(0.5F, -8F, 0.5F, 2, 5, 2);
      le.setRotationPoint(0F, 0F, 0F);
      le.setTextureSize(128, 128);
      le.mirror = true;
      setRotation(le, 0F, 0F, 0.2617994F);
      lf = new ModelRenderer(this, 48, 50);
      lf.addBox(3.5F, -7F, 0F, 1, 5, 1);
      lf.setRotationPoint(0f,0f,0f);
      lf.setTextureSize(128, 128);
      lf.mirror = true;
      setRotation(lf, 0F, 0F, 0F);
      lg = new ModelRenderer(this, 50, 94);
      lg.addBox(0F, -7F, 1.8F, 2, 6, 1);
      lg.setRotationPoint(0f,0f,0f);
      lg.setTextureSize(128, 128);
      lg.mirror = true;
      setRotation(lg, 0F, 0F, 0F);
      lh = new ModelRenderer(this, 50, 102);
      lh.addBox(2F, -6F, 1.8F, 1, 5, 1);
      lh.setRotationPoint(0f,0f,0f);
      lh.setTextureSize(128, 128);
      lh.mirror = true;
      setRotation(lh, 0F, 0F, 0F);
      li = new ModelRenderer(this, 44, 69);
      li.addBox(-1F, 0F, -2.3F, 4, 3, 1);
      li.setRotationPoint(0f,0f,0f);
      li.setTextureSize(128, 128);
      li.mirror = true;
      setRotation(li, 0F, 0F, 0F);
      lj = new ModelRenderer(this, 44, 69);
      lj.addBox(-1F, 0F, 1.3F, 4, 3, 1);
      lj.setRotationPoint(0f,0f,0f);
      lj.setTextureSize(128, 128);
      lj.mirror = true;
      setRotation(lj, 0F, 0F, 0F);
      lk = new ModelRenderer(this, 44, 75);
      lk.addBox(3F, 0F, -2F, 1, 3, 4);
      lk.setRotationPoint(0f,0f,0f);
      lk.setTextureSize(128, 128);
      lk.mirror = true;
      setRotation(lk, 0F, 0F, 0F);
      ll = new ModelRenderer(this, 48, 58);
      ll.addBox(3F, -3F, -1F, 1, 2, 2);
      ll.setRotationPoint(0f,0f,0f);
      ll.setTextureSize(128, 128);
      ll.mirror = true;
      setRotation(ll, 0F, 0F, 0.7679449F);
      lfa = new ModelRenderer(this, 56, 37);
      lfa.addBox(-2F, 7F, -2.5F, 5, 5, 5);
      lfa.setRotationPoint(0F, 0F, 0F);
      lfa.setTextureSize(128, 128);
      lfa.mirror = true;
      setRotation(lfa, 0F, 0F, 0F);
      lfb = new ModelRenderer(this, 56, 49);
      lfb.addBox(5F, 4F, -1F, 1, 5, 2);
      lfb.setRotationPoint(0F, 0F, 0F);
      lfb.setTextureSize(128, 128);
      lfb.mirror = true;
      setRotation(lfb, 0F, 0F, 0.3490659F);
      rfa = new ModelRenderer(this, 56, 37);
      rfa.addBox(-3F, 7F, -2.5F, 5, 5, 5);
      rfa.setRotationPoint(0F, 0F, 0F);
      rfa.setTextureSize(128, 128);
      rfa.mirror = true;
      setRotation(rfa, 0F, 0F, 0F);
      rfb = new ModelRenderer(this, 56, 49);
      rfb.addBox(-6F, 4F, -1F, 1, 5, 2);
      rfb.setRotationPoint(0F, 0F, 0F);
      rfb.setTextureSize(128, 128);
      rfb.mirror = true;
      setRotation(rfb, 0F, 0F, -0.3490659F);
      
      bipedHead.addChild(heada);
      bipedHead.addChild(headb);
      bipedHead.addChild(headc);
      bipedHead.addChild(headd);
      bipedHead.addChild(heade);
      bipedHead.addChild(headf);
      bipedHead.addChild(headg);
      bipedHead.addChild(headh);
      bipedHead.addChild(headi);
      bipedHead.addChild(headj);
      bipedHead.addChild(headk);
      bipedHead.addChild(headl);
      bipedHead.addChild(headm);
      bipedHead.addChild(headn);
      bipedHead.addChild(heado);
      bipedHead.addChild(headp);
      bipedHead.addChild(headq);
      bipedBody.addChild(ba);
      bipedBody.addChild(bb);
      bipedBody.addChild(bc);
      bipedBody.addChild(bd);
      bipedBody.addChild(be);
      bipedBody.addChild(bf);
      bipedBody.addChild(bg);
      bipedBody.addChild(bh);
      bipedRightArm.addChild(ra);
      bipedRightArm.addChild(rb);
      bipedRightArm.addChild(rc);
      bipedRightArm.addChild(rd);
      bipedRightArm.addChild(re);
      bipedRightArm.addChild(rf);
      bipedRightArm.addChild(rg);
      bipedRightArm.addChild(rh);
      bipedRightArm.addChild(ri);
      bipedRightArm.addChild(rj);
      bipedRightArm.addChild(rk);
      bipedLeftArm.addChild(la);
      bipedLeftArm.addChild(lb);
      bipedLeftArm.addChild(lc);
      bipedLeftArm.addChild(ld);
      bipedLeftArm.addChild(le);
      bipedLeftArm.addChild(lf);
      bipedLeftArm.addChild(lg);
      bipedLeftArm.addChild(lh);
      bipedLeftArm.addChild(li);
      bipedLeftArm.addChild(lj);
      bipedLeftArm.addChild(lk);
      bipedLeftArm.addChild(ll);
      bipedLeftLeg.addChild(lfa);
      bipedLeftLeg.addChild(lfb);
      bipedRightLeg.addChild(rfa);
      bipedRightLeg.addChild(rfb);
      }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity e)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5,e);
  }

}
