//Laptop computer: adds screen size to other Computer info

public final class Laptop
{
    private final Computer comp;
    private final String screenSize;

    public Laptop(String CPU, String RAM, String disk, String screenSize)
    {
        //Composition
        this.comp = new Computer(CPU, RAM, disk);

        //Only in Laptop subclass
        this.screenSize=screenSize;
    }

    //Getters
    public String getScreenSize()
    {
        return this.screenSize;
    }

    public Computer getComputer()
    {
        return this.comp;
    }

    //Return formatted version of data
    public String toString()
    {
        return "Type:Laptop\tCPU:" + this.comp.getCPU() + "\tRAM:" + this.comp.getRAM() + "\tDisk:" + this.comp.getDisk() + "\tScreen:" + this.screenSize;
    }
    
}