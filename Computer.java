//Computer class: manages computer CPU, RAM and Disk information

public class Computer {
    //made attributes private
    private String CPU=null;
    private String RAM=null;
    private String disk=null;

    //Constructors
    public Computer() {} //No-arg constructor

    public Computer(String CPU, String RAM, String disk) {
        this.CPU=CPU;
        this.RAM=RAM;
        this.disk=disk;
    }
    //Got rid of the setters

    //Getters
    public String getCPU() {
        return this.CPU;
    }

    public String getRAM() {
        return this.RAM;
    }

    public String getDisk() {
        return this.disk;
    }


}