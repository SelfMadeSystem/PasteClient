/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uwu.smsgamer.PasteClient.scripting.runtime.minecraft.block.material;

import net.minecraft.block.material.Material;

public class WrapperMaterial {
    private Material real;

    public WrapperMaterial(Material var1) {
        this.real = var1;
    }

    public Material unwrap() {
        return this.real;
    }

    public boolean isLiquid() {
        return this.real.isLiquid();
    }

    public boolean isSolid() {
        return this.real.isSolid();
    }

    public boolean blocksLight() {
        return this.real.blocksLight();
    }

    public boolean blocksMovement() {
        return this.real.blocksMovement();
    }

    public boolean getCanBurn() {
        return this.real.getCanBurn();
    }

    public WrapperMaterial setReplaceable() {
        return new WrapperMaterial(this.real.setReplaceable());
    }

    public boolean isReplaceable() {
        return this.real.isReplaceable();
    }

    public boolean isOpaque() {
        return this.real.isOpaque();
    }

    public boolean isToolNotRequired() {
        return this.real.isToolNotRequired();
    }

    public int getMaterialMobility() {
        return this.real.getMaterialMobility();
    }

    public WrapperMaterial getAir() {
        return new WrapperMaterial(Material.AIR);
    }

    public WrapperMaterial getGrass() {
        return new WrapperMaterial(Material.GRASS);
    }

    public WrapperMaterial getGround() {
        return new WrapperMaterial(Material.GROUND);
    }

    public WrapperMaterial getWood() {
        return new WrapperMaterial(Material.WOOD);
    }

    public WrapperMaterial getRock() {
        return new WrapperMaterial(Material.ROCK);
    }

    public WrapperMaterial getIron() {
        return new WrapperMaterial(Material.IRON);
    }

    public WrapperMaterial getAnvil() {
        return new WrapperMaterial(Material.ANVIL);
    }

    public WrapperMaterial getWater() {
        return new WrapperMaterial(Material.WATER);
    }

    public WrapperMaterial getLava() {
        return new WrapperMaterial(Material.LAVA);
    }

    public WrapperMaterial getLeaves() {
        return new WrapperMaterial(Material.LEAVES);
    }

    public WrapperMaterial getPlants() {
        return new WrapperMaterial(Material.PLANTS);
    }

    public WrapperMaterial getVine() {
        return new WrapperMaterial(Material.VINE);
    }

    public WrapperMaterial getSponge() {
        return new WrapperMaterial(Material.SPONGE);
    }

    public WrapperMaterial getCloth() {
        return new WrapperMaterial(Material.CLOTH);
    }

    public WrapperMaterial getFire() {
        return new WrapperMaterial(Material.FIRE);
    }

    public WrapperMaterial getSand() {
        return new WrapperMaterial(Material.SAND);
    }

    public WrapperMaterial getCircuits() {
        return new WrapperMaterial(Material.CIRCUITS);
    }

    public WrapperMaterial getCarpet() {
        return new WrapperMaterial(Material.CARPET);
    }

    public WrapperMaterial getGlass() {
        return new WrapperMaterial(Material.GLASS);
    }

    public WrapperMaterial getRedstoneLight() {
        return new WrapperMaterial(Material.REDSTONE_LIGHT);
    }

    public WrapperMaterial getTnt() {
        return new WrapperMaterial(Material.TNT);
    }

    public WrapperMaterial getCoral() {
        return new WrapperMaterial(Material.CORAL);
    }

    public WrapperMaterial getIce() {
        return new WrapperMaterial(Material.ICE);
    }

    public WrapperMaterial getPackedIce() {
        return new WrapperMaterial(Material.PACKED_ICE);
    }

    public WrapperMaterial getSnow() {
        return new WrapperMaterial(Material.SNOW);
    }

    public WrapperMaterial getCraftedSnow() {
        return new WrapperMaterial(Material.CRAFTED_SNOW);
    }

    public WrapperMaterial getCactus() {
        return new WrapperMaterial(Material.CACTUS);
    }

    public WrapperMaterial getClay() {
        return new WrapperMaterial(Material.CLAY);
    }

    public WrapperMaterial getGourd() {
        return new WrapperMaterial(Material.GOURD);
    }

    public WrapperMaterial getDragonEgg() {
        return new WrapperMaterial(Material.DRAGON_EGG);
    }

    public WrapperMaterial getPortal() {
        return new WrapperMaterial(Material.PORTAL);
    }

    public WrapperMaterial getCake() {
        return new WrapperMaterial(Material.CAKE);
    }

    public WrapperMaterial getWeb() {
        return new WrapperMaterial(Material.WEB);
    }

    public WrapperMaterial getPiston() {
        return new WrapperMaterial(Material.PISTON);
    }

    public WrapperMaterial getBarrier() {
        return new WrapperMaterial(Material.BARRIER);
    }
}
