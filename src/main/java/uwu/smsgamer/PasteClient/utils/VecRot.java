package uwu.smsgamer.PasteClient.utils;

import net.minecraft.util.math.Vec3d;

public class VecRot {
    private Rotation rotation;
    private Vec3d vec;

    public VecRot(Vec3d vec, Rotation rotation){
        this.vec = vec;
        this.rotation = rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public void setVec(Vec3d vec) {
        this.vec = vec;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Vec3d getVec() {
        return vec;
    }
}
