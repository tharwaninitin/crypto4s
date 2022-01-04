package crypto4s

import org.mindrot.jbcrypt.BCrypt
import zio.test.Assertion.equalTo
import zio.test._

object CryptoTestSuite extends DefaultRunnableSpec {
  val api: Live = crypto4s.Live(None)

  def spec: ZSpec[environment.TestEnvironment, Any] =
    suite("Crypto Test Suite")(
      test("Encrypt should encrypt string correctly") {
        val encryptedValue = api.encrypt("admin")
        assertTrue(encryptedValue == "TY216IXBVLDFBZIHfNWtBQ==")
      },
      test("Decrypt should decrypt string correctly") {
        val decryptedValue = api.decrypt("TY216IXBVLDFBZIHfNWtBQ==")
        assertTrue(decryptedValue == "admin")
      },
      test("Asymmetric encryption should work without salt") {
        val condition = BCrypt.checkpw("abc", api.oneWayEncrypt("abc"))
        assertTrue(condition)
      },
      test("Asymmetric encryption should work with salt default value 10") {
        val condition = BCrypt.checkpw("abc", api.oneWayEncrypt("abc", Some(10)))
        assertTrue(condition)
      },
      test("Asymmetric encryption should not work with salt 5 and different values ") {
        val condition = BCrypt.checkpw("abc123", api.oneWayEncrypt("abc", Some(5)))
        assert(condition)(equalTo(false))
      },
      test("Asymmetric encryption should not work with different values") {
        val condition = BCrypt.checkpw("abc123", api.oneWayEncrypt("abc"))
        assert(condition)(equalTo(false))
      }
    ) @@ TestAspect.flaky
}
