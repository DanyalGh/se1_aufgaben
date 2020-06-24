import java.io.FilterInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

public class UpperCaseStream extends FilterInputStream {
    protected UpperCaseStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException{
        int w = super.read();

        if(w != -1){
            w = Character.toUpperCase((char)w);
        }
        return w;
    }

    @Override
    public int read(byte[] b, int stop, int len) throws IOException{
        int retVal = super.read(b,stop,len);
        for(int i = stop; i<stop+retVal; i++){
            b[i] = (byte)Character.toUpperCase((char)b[i]);
        }
        return retVal;
    }
}
