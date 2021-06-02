package rs.ac.uns.ftn.sbnz.rentcarservice.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import rs.ac.uns.ftn.sbnz.rentcarservice.tests.cep.CEPTests;
import rs.ac.uns.ftn.sbnz.rentcarservice.tests.filter.FilterTests;
import rs.ac.uns.ftn.sbnz.rentcarservice.tests.popusti.PopustiTests;
import rs.ac.uns.ftn.sbnz.rentcarservice.tests.query.QueryTests;
import rs.ac.uns.ftn.sbnz.rentcarservice.tests.rangiranje.BudzetRangiranjeTests;
import rs.ac.uns.ftn.sbnz.rentcarservice.tests.statusi.StatusiTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FilterTests.class,
        BudzetRangiranjeTests.class,
        PopustiTests.class,
        StatusiTests.class,
        CEPTests.class,
        QueryTests.class
})
public class AllTestsSuite {
}
