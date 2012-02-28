package serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class ThreadContextObjectInputStream extends ObjectInputStream {

  protected ThreadContextObjectInputStream() throws IOException, SecurityException {
    super();
  }

  public ThreadContextObjectInputStream(InputStream in) throws IOException {
    super(in);
  }

  @Override
  protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
    String name = desc.getName();

    return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
  }

}
