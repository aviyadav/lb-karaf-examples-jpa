package lb.examples.karaf.jpa.hibernate.cmd;

import lb.examples.karaf.jpa.hibernate.data.IDataService;
import org.apache.karaf.shell.console.OsgiCommandSupport;

/**
 *
 */
public abstract class AbstractItemCommand extends OsgiCommandSupport {
    private IDataService m_dataService;

    public void setDataService(IDataService dataService) {
        m_dataService = dataService;
    }

    public IDataService getDataService() {
        return m_dataService;
    }
}
