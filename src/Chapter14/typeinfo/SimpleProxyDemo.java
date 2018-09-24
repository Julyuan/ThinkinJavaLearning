package Chapter14.typeinfo;
import javafx.beans.binding.ObjectExpression;
import sun.java2d.pipe.SpanShapeRenderer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.*;
import static net.mindview.util.Print.*;

interface Interface{
    void doSomething();
    void somethingElse(String arg);
}

class RealObject implements Interface{
    public void doSomething(){
        print("doSomething");
    }
    public void somethingElse(String arg){
        print("Something else " + arg);
    }
}

class SimpleProxy implements Interface{
    private Interface proxied;
    public SimpleProxy(Interface proxied){
        this.proxied = proxied;
    }

    public void doSomething(){
        print("SimpleProxy doSomething");
        proxied.doSomething();
    }

    public void somethingElse(String arg){
        print("SimpleProxy somethingElse "+arg);
        proxied.somethingElse(arg);
    }
}


class DynamicProxyHandler implements InvocationHandler{
    private Object proxied;
    public DynamicProxyHandler(Object proxied){
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
    throws Throwable{
        System.out.println("**** proxy: "+proxy.getClass()+
                ", method: "+method+" ,args: "+args);
        if(args!=null){
            for(Object arg: args){
                System.out.println(" "+args);
            }
        }
        return method.invoke(proxied, args);
    }
}
public class SimpleProxyDemo {
    public static void consumer(Interface iface){
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args){
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}



