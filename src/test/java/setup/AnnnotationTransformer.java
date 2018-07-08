package setup;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        TestCaseScenario testCaseScenario = TestCaseExecutor.getTestScenario(method.getName());
        if(testCaseScenario.getExecuteStatus()=="false"){
            iTestAnnotation.setEnabled(false);
        }

    }
}
