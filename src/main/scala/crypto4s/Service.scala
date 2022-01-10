package crypto4s

trait Service {
  def encrypt(text: String): String
  def decrypt(text: String): String
  def oneWayEncrypt(text: String, salt: Option[Int] = None): String
}
