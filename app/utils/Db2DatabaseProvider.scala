package utils

import com.google.inject.Provider
import slick.driver.H2Driver.api._

/**
  * Created by mprzybylak on 16.03.2017.
  */
object Db2DatabaseProvider extends Provider[Database] {
  override def get(): Database = Database.forConfig("inMemoryDb")
}
