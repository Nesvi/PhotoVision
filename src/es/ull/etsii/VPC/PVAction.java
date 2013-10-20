package es.ull.etsii.VPC;

public class PVAction {
   
   public String name;
   protected static Controller controller;
   
   public PVAction(String Name){
      name = Name;
      controller.addPVAction(this);
   }
   
   public static void setController(Controller control){
      controller = control;
   }
   
   public void execute(){
      
   }
}
