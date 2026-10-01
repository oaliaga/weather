package com.oso.weather

import com.oso.weather.common.utils.FormatUtilsTest
import com.oso.weather.weather.domain.DataSourceTest
import com.oso.weather.weather.model.RemoteDatabaseTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    FormatUtilsTest::class,
    RemoteDatabaseTest::class,
    DataSourceTest::class
)
class AllTests {
}