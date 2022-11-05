package com.greatorator.tolkienmobs.entity.ambient.model;

/** Borrowed from Twilight Forest */
//
//public class SwarmModel extends SegmentedModel<SwarmEntity> {
//    ModelRenderer core;
//    ModelRenderer node1;
//    ModelRenderer node2;
//    ModelRenderer node3;
//    ModelRenderer node4;
//    ModelRenderer node5;
//    ModelRenderer node6;
//
//    Random rand = new Random();
//
//    public SwarmModel() {
//        core = new ModelRenderer(this, rand.nextInt(28), rand.nextInt(28));
//        core.addBox(-4F, 0.0F, -2F, 1, 1, 1);
//        core.setPos(0.0F, -4.0F, 0.0F);
//
//        node1 = new ModelRenderer(this, rand.nextInt(28), rand.nextInt(28));
//        node1.addBox(-5.5F, -5F, -13F, 1, 1, 1);
//        node1.setPos(2F, -1F, -6F);
//        core.addChild(node1);
//
//        node2 = new ModelRenderer(this, rand.nextInt(28), rand.nextInt(28));
//        node2.addBox(-5.5F, -13F, -5F, 1, 1, 1);
//        node2.setPos(0F, -7F, -1F);
//        core.addChild(node2);
//
//        node3 = new ModelRenderer(this, rand.nextInt(28), rand.nextInt(28));
//        node3.addBox(-13F, -5F, -5F, 1, 1, 1);
//        node3.setPos(5F, -2F, -1F);
//        core.addChild(node3);
//
//        node4 = new ModelRenderer(this, rand.nextInt(28), rand.nextInt(28));
//        node4.addBox(-5.5F, -5F, -13F, 1, 1, 1);
//        node4.setPos(2F, -1F, -6F);
//        core.addChild(node4);
//
//        node5 = new ModelRenderer(this, rand.nextInt(28), rand.nextInt(28));
//        node5.addBox(-5.5F, -13F, -5F, 1, 1, 1);
//        node5.setPos(0F, -7F, -1F);
//        core.addChild(node5);
//
//        node6 = new ModelRenderer(this, rand.nextInt(28), rand.nextInt(28));
//        node6.addBox(-13F, -5F, -5F, 1, 1, 1);
//        node6.setPos(5F, -2F, -1F);
//        core.addChild(node6);
//
//        addBugsToNodes(node1);
//        addBugsToNodes(node2);
//        addBugsToNodes(node3);
//        addBugsToNodes(node4);
//        addBugsToNodes(node5);
//        addBugsToNodes(node6);
//    }
//
//    @Override
//    public Iterable<ModelRenderer> parts() {
//        return ImmutableList.of(core);
//    }
//
//    /**
//     * Add the proper number of mosquitoes to the nodes
//     */
//    public void addBugsToNodes(ModelRenderer node) {
//        int bugs = 16;
//
//        for (int i = 0; i < bugs; i++) {
//            Vector3d vec = new Vector3d(11, 0, 0);
//            float rotateY = ((i * (360F / bugs)) * 3.141593F) / 180F;
//            vec.yRot(rotateY);
//            ModelRenderer bug = new ModelRenderer(this, rand.nextInt(28), rand.nextInt(28));
//
//            float bugX = (rand.nextFloat() - rand.nextFloat()) * 4.0f;
//            float bugY = (rand.nextFloat() - rand.nextFloat()) * 4.0f;
//            float bugZ = (rand.nextFloat() - rand.nextFloat()) * 4.0f;
//
//            bug.addBox(bugX, bugY, bugZ, 1, 1, 1);
//
//            bug.setPos((float) vec.x, (float) vec.y, (float) vec.z);
//            bug.yRot = rotateY;
//            node.addChild(bug);
//        }
//    }
//
//    @Override
//    public void setupAnim(SwarmEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) { }
//
//
//    @Override
//    public void prepareMobModel(SwarmEntity entity, float limbSwing, float limbSwingAmount, float partialTicks) {
//        core.yRot = (entity.tickCount + partialTicks) / 5.0F;
//        core.xRot = MathHelper.sin((entity.tickCount + partialTicks) / 5.0F) / 4.0F;
//        core.zRot = MathHelper.cos((entity.tickCount + partialTicks) / 5.0F) / 4.0F;
//
//        node1.yRot = (entity.tickCount + partialTicks) / 2.0F;
//        node1.xRot = MathHelper.sin((entity.tickCount + partialTicks) / 6.0F) / 2.0F;
//        node1.zRot = MathHelper.cos((entity.tickCount + partialTicks) / 5.0F) / 4.0F;
//
//        node2.yRot = MathHelper.sin((entity.tickCount + partialTicks) / 2.0F) / 3.0F;
//        node2.xRot = (entity.tickCount + partialTicks) / 5.0F;
//        node2.zRot = MathHelper.cos((entity.tickCount + partialTicks) / 5.0F) / 4.0F;
//
//        node3.yRot = MathHelper.sin((entity.tickCount + partialTicks) / 7.0F) / 3.0F;
//        node3.xRot = MathHelper.cos((entity.tickCount + partialTicks) / 4.0F) / 2.0F;
//        node3.zRot = (entity.tickCount + partialTicks) / 5.0F;
//
//        node4.xRot = (entity.tickCount + partialTicks) / 2.0F;
//        node4.zRot = MathHelper.sin((entity.tickCount + partialTicks) / 6.0F) / 2.0F;
//        node4.yRot = MathHelper.sin((entity.tickCount + partialTicks) / 5.0F) / 4.0F;
//
//        node5.zRot = MathHelper.sin((entity.tickCount + partialTicks) / 2.0F) / 3.0F;
//        node5.yRot = MathHelper.cos((entity.tickCount + partialTicks) / 5.0F) / 4.0F;
//        node5.xRot = MathHelper.cos((entity.tickCount + partialTicks) / 5.0F) / 4.0F;
//
//        node6.zRot = MathHelper.cos((entity.tickCount + partialTicks) / 7.0F) / 3.0F;
//        node6.xRot = MathHelper.cos((entity.tickCount + partialTicks) / 4.0F) / 2.0F;
//        node6.yRot = (entity.tickCount + partialTicks) / 5.0F;
//    }
//}