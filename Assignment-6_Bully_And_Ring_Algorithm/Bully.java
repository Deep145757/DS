import java.util.*;


class Bully {
    private List<Integer> processes;
    private Integer leader;

    public Bully(List<Integer> processes){
        this.processes = processes;
        this.leader = null;
    }

    public void startElection(int initiator){
        System.out.println("Process " + initiator + " started an Election.");
        List <Integer> higherProcesses = new ArrayList<>();

        for(int process : processes){
            if(process > initiator){
                higherProcesses.add(process);
            }
        }
        Scanner sc = new Scanner(System.in);

        if(higherProcesses.isEmpty()){
            leader = initiator;
            System.out.println("Process " + initiator + " is new leader");
        }
        else{
            boolean responseRecv = false;
            for(int process : higherProcesses){
                System.out.println("Process " + initiator + " send election message to " + process);
                System.out.print("Should process " + process + " Respond? (1/0) : ");
                int response = sc.nextInt();

                if(response == 1){
                    responseRecv = 1;
                    System.out.println("Process " + process + " responded and it will start its own Election...");
                    startElection(process);
                    return ;
                }
            }

            if(!responseRecv){
                System.out.println("No responce recieved from higher processes.");;
                System.out.println("Process " + initiator + " is new leader.");
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes : ");
        int n = sc.nextInt();

        List<Integer> processes = new ArrayList<>();
        System.out.println("Enter process IDs : ");
        for(int i=0; i<n; i++){
            processes.add(sc.nextInt());
        }

        System.out.print("Enter the initiator process ID : ");
        int initiator = sc.nextInt();

        Bully bl = new Bully(processes);
        bl.startElection(initiator);

    }
}
