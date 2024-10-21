//Desktop computer: adds GPU type

public class Desktop {
  private final Computer computer; //Composition: Desktop has a Computer
  private final String GPUType;

  //Constructors
  public Desktop(Computer computer, String GPUType) {
    this.computer = computer;
    this.GPUType = GPUType;

  }

  //Getter
  public Computer getComputer() {
    return computer;
  }


  //Getter
  public String getGPUType() {
    return GPUType;
  }

  //Return formatted version of data
  public String toString() {
    return "Type: Desktop\tCPU: " + computer.getCPU() + "\tRAM: " + computer.getRAM() +
            "\tDisk: " + computer.getDisk() + "\tGPU: " + GPUType;
  }

}