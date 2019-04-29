package com.steppers.state;

import com.badlogic.gdx.graphics.Texture;

public class Spell {

    public interface CastFunc {
        boolean cast(Spell spell, Being caster, Being target);
    }

    private CastFunc castFunc;

    private float baseStat, statModifier;
    private float baseSuccessRate, successRateModifier;

    public Spell(Texture icon) {
        castFunc = (Spell spell, Being caster, Being target) -> false; // default cast func
    }

    public void setCastFunc(CastFunc func) {
        castFunc = func;
    }

    public void cast(Being caster, Being target) {
        castFunc.cast(this, caster, target);
    }

    public void setBaseStat(float baseStat) {
        this.baseStat = baseStat;
    }

    public void setBaseSuccessRate(float baseSuccessRate) {
        this.baseSuccessRate = baseSuccessRate;
    }

    public void addStatModifier(float modifier) {
        statModifier += modifier;
    }

    public void addSuccessRateModifier(float modifier) {
        successRateModifier += modifier;
    }

    public float getStatModifier() {
        return statModifier;
    }

    public float getSuccessRateModifier() {
        return statModifier;
    }

    public float getBaseStat() {
        return baseStat;
    }

    public float getBaseSuccessRate() {
        return baseSuccessRate;
    }

    public float getStat() {
        return baseStat * statModifier;
    }

    public float getSuccessRate() {
        return baseSuccessRate * successRateModifier;
    }

}
