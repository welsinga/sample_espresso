package espresspo.sample.elsinga.com.espressosample.util;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ActivityRule <T extends Activity> implements TestRule {
    private final Class<T> mActivityClass;

    private T mActivity;
    private Instrumentation mInstrumentation;

    public ActivityRule(Class<T> activityClass) {
        mActivityClass = activityClass;
    }

    protected Intent getLaunchIntent(String targetPackage, Class<T> activityClass) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName(targetPackage, activityClass.getName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

//    public final T get() {
//        launchActivity();
//        return mActivity;
//    }
//
//    public final Instrumentation instrumentation() {
//        launchActivity();
//        return mInstrumentation;
//    }

    @Override
    public final Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                launchActivity();

                base.evaluate();

                if (!mActivity.isFinishing()) {
                    mActivity.finish();
                }
                mActivity = null; // Eager reference kill in case someone leaked our reference.
            }
        };
    }

    private Instrumentation fetchInstrumentation() {
        Instrumentation result = mInstrumentation;
        return result != null ? result
                : (mInstrumentation = InstrumentationRegistry.getInstrumentation());
    }

    @SuppressWarnings("unchecked") // Guarded by generics at the constructor.
    private void launchActivity() {
        if (mActivity != null) return;

        Instrumentation instrumentation = fetchInstrumentation();

        String targetPackage = instrumentation.getTargetContext().getPackageName();
        Intent intent = getLaunchIntent(targetPackage, mActivityClass);

        mActivity = (T) instrumentation.startActivitySync(intent);
        instrumentation.waitForIdleSync();
    }
}
