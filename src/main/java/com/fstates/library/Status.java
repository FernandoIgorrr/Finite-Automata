package com.fstates.library;

public class Status {
    //Proriedades de combate:
    //-- Geral
    private int health;
    
    //-- Combate físico
    private int attackDamage;
    private int armor;

    private double physicalCriticalHit;

    //-- Combate magico
    private int spellPower;
    private int magicResist;

    private double magicalCriticalHit;

    double criticalDamage;

    private final int meleeRange;
    private final int rangedRange;

    private final boolean melee;
    private final boolean ranged;

    //Quanto mais haste menor o tempo para carregar e lançar o feitiço/magia/encanto
    private int haste;

    public Status(){
        melee               = StatusBase.BASE_MELEE;
        ranged              = StatusBase.BASE_MELEE;

        meleeRange          = StatusBase.BASE_MELEE_RANGE;
        rangedRange         = StatusBase.BASE_RANGED_RANGE;

        fillBaseStatus();
    }

    public Status(int attackDamage, int armor, int spellPower, int magicResist){
        this();

        this.attackDamage        = attackDamage;
        this.spellPower          = spellPower;

        this.armor               = armor;
        this.magicResist         = magicResist;
    }

    private void fillBaseStatus(){
        health              = StatusBase.BASE_HEALTH;

        attackDamage        = StatusBase.BASE_ATTACK_DAMAGE;
        spellPower          = StatusBase.BASE_SPELL_POWER;

        armor               = StatusBase.BASE_ARMOR;
        magicResist         = StatusBase.BASE_MAGIC_RESIST;

        physicalCriticalHit = StatusBase.BASE_PHYSICAL_CRITICAL_HIT;
        magicalCriticalHit  = StatusBase.BASE_MAGICAL_CRITICAL_HIT;

        haste               = StatusBase.BASE_HASTE;

        criticalDamage      = StatusBase.BASE_CRITICAL_DAMAGE;
    }

    // Setters e Getter dos atributos de combate
    //--health
    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health           = health;
    }

    public void addsHealth(int additionalHealth)
    {
        health                += additionalHealth;
    }

    //--attackDamage
    public int getAttackDamage()
    {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage)
    {
        this.attackDamage           = attackDamage;
    }

    public void addsAttackDamage(int additionalAttack)
    {
        attackDamage                += additionalAttack;
    }

    //--armor
    public int getArmor()
    {
        return armor;
    }

    public void setArmor(int armor)
    {
        this.armor                  = armor;
    }

    public void addsArmor(int additionalArmor)
    {
        armor                       = additionalArmor;
    }

    //--physicalCriticalHit
     public double getPhysicalCriticalHit()
    {
        return physicalCriticalHit;
    }

    public void setphysicalCriticalHit(int physicalCriticalHit)
    {
        this.physicalCriticalHit    = physicalCriticalHit;
    }

    public void addsphysicalCriticalHit(double additionalPhysicalCriticalHit)
    {
        physicalCriticalHit         += additionalPhysicalCriticalHit;
    }

    //--spellPower
    public int getSpellPower()
    {
        return spellPower;
    }

    public void setSpellPower(int spellPower)
    {
        this.spellPower             = spellPower;
    }

    public void addsSpellPower(int additionalSpellPower)
    {
        spellPower                  += additionalSpellPower;
    }

    //--magicResist
    public int getMagicResist()
    {
        return magicResist;
    }

    public void setMagicResist(int magicResist)
    {
        this.magicResist            = magicResist;
    }

    public void addsMagicResist(int additionalMagicResist)
    {
        magicResist                 = additionalMagicResist;
    }

    //--magicResist
    public double getMagicalCriticalHit()
    {
        return magicalCriticalHit;
    }

    public void setMagicalCriticalHit(double magicalCriticalHit)
    {
        this.magicalCriticalHit     = magicalCriticalHit;
    }

    public void addsMagicalCriticalHit(double additionalMagicalCriticalHit)
    {
        magicalCriticalHit          += additionalMagicalCriticalHit;
    }

}
