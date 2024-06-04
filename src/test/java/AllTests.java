import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"auth", "central", "graduationSystem"})
public class AllTests {}
