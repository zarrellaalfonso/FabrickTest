import com.company.Manager.ArgsManager;
import com.company.Model.ArgCheckResponse;
import org.junit.Assert;
import org.junit.Test;

public class ArgManagerTest {
    @Test
    public void no_args() {
        String[] args = {};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(!resp.isOk());
    }

    @Test
    public void unavailable_operation() {
        String[] args = {"aaa"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(!resp.isOk());
    }

    @Test
    public void getSaldo_noAccountId() {
        String[] args = {"getSaldo"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(!resp.isOk());
    }

    @Test
    public void getSaldo_ok() {
        String[] args = {"getSaldo","aaa"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(resp.isOk());
    }

    @Test
    public void getTransazioni_noAccountId() {
        String[] args = {"getTransazioni"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(!resp.isOk());
    }

    @Test
    public void getTransazioni_noStartDate() {
        String[] args = {"getTransazioni","aaa"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(!resp.isOk());
    }

    @Test
    public void getTransazioni_invalidStartDate() {
        String[] args = {"getTransazioni","aaa", "bbb", "ccc"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(!resp.isOk());
    }

    @Test
    public void getTransazioni_noEndDate() {
        String[] args = {"getTransazioni","aaa","bbb"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(!resp.isOk());
    }

    @Test
    public void getTransazioni_invalidEndDate() {
        String[] args = {"getTransazioni","aaa", "2020-01-01", "ccc"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(!resp.isOk());
    }

    @Test
    public void getTransazioni_ok() {
        String[] args = {"getTransazioni","aaa","2020-01-01","2020-12-31"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(resp.isOk());
    }

    @Test
    public void postBonifico_noAccountId() {
        String[] args = {"postBonifico"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(!resp.isOk());
    }

    @Test
    public void postBonifico_noPayload() {
        String[] args = {"postBonifico","aaa"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(!resp.isOk());
    }

    @Test
    public void postBonifico_unvalidPayload() {
        String[] args = {"postBonifico","aaa","bbb","ccc"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(!resp.isOk());
    }

    @Test
    public void postBonifico_ok() {
        String[] args = {"postBonifico","aaa","bbb"};
        ArgsManager argsManager = new ArgsManager();
        ArgCheckResponse resp = argsManager.argsCheck(args);
        Assert.assertTrue(resp.isOk());
    }
}
