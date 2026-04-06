import ai.onnxruntime.*;
import java.util.Collections;

public class TestOnnx {
    public static void main(String[] args) throws Exception {
        OrtEnvironment env = OrtEnvironment.getEnvironment();
        OrtSession session = env.createSession("models/siglip2_vision.onnx", new OrtSession.SessionOptions());
        for (ai.onnxruntime.NodeInfo info : session.getOutputInfo().values()) {
            System.out.println(info.getName() + " -> " + info.getInfo().toString());
        }
    }
}
