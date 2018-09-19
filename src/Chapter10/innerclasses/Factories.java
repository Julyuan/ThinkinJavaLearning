package Chapter10.innerclasses;
import org.omg.CORBA.PUBLIC_MEMBER;

import static net.mindview.util.Print.*;

interface Service{
    void method1();
    void method2();
}

interface ServiceFactory{
    Service getService();
}

class Implementation1 implements Service{
    private Implementation1(){}
    public void method1(){
        print("Implementation1 method1");
    }

    public void method2(){
        print("Implementation method2");
    }

    public static ServiceFactory factory =
            new ServiceFactory() {
                public Service getService() {
                    return new Implementation1();
                }
            };
}

class Implementation2 implements Service{
    private Implementation2(){}

    @Override
    public void method1() {
        print("Implementaiton2 method1");
    }

    public void method2(){
        print("Implementation2 method2");
    }

    public static ServiceFactory factory =
            new ServiceFactory() {
                @Override
                public Service getService() {
                    return new Implementation2();
                }
            };
}

public class Factories {
    public static void serviceConsumer(ServiceFactory fact){
        Service s = fact.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args){
        serviceConsumer(Implementation1.factory);
        serviceConsumer(Implementation2.factory);
    }

}
