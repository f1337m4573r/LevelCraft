package me.f1337.levelcraftcore;

/**
 * Work in progress
 * @author Kratharin
 *
 */
public class ExpCacheData
{
    private Double value;
    private boolean changed = true;

    public ExpCacheData(Double value)
    {
        this.value = value;
        this.changed = true;
    }

    public Double getValue()
    {
        return value;
    }

    public void setValue(Double value)
    {
        this.value = value;
        this.changed = true;
    }

    public boolean isChanged()
    {
        return changed;
    }
    public void setChanged(boolean changed)
    {
        this.changed = changed;
    }

}
