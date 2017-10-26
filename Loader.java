import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

public class Loader {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: Loader <pid> <agent>");
            return;
        }

        Loader ldr = new Loader(args[0], args[1]);
        ldr.install();
    }

    private String pid;
    private String agent;
    private VirtualMachine vm;

    public Loader(String pid, String agent) {
        this.pid = pid;
        this.agent = agent;
    }

    public void install() {
        if (!attach()) return;
        loadAgent();
        detach();
    }

    private boolean attach() {
        try {
            vm = VirtualMachine.attach(pid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean loadAgent() {
        try {
            vm.loadAgent(agent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean detach() {
        try {
            vm.detach();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
