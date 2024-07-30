object q2 {
  import scala.io.StdIn._

  private def getStudentInfo: (String, Int, Int, Double, Char) = {
    println("Enter student name: ")
    val name = readLine().trim
    println("Enter marks obtained: ")
    val marks = readInt()
    println("Enter total possible marks: ")
    val totalMarks = readInt()

    val (isValid, errorMessage) = validateInput(name, marks, totalMarks)
    if (!isValid) {
      throw new IllegalArgumentException(errorMessage.getOrElse("Invalid input"))
    }

    val percentage = (marks.toDouble / totalMarks) * 100
    val grade = calculateGrade(percentage)

    (name, marks, totalMarks, percentage, grade)
  }

  private def validateInput(name: String, marks: Int, totalMarks: Int): (Boolean, Option[String]) = {
    if (name.isEmpty) {
      (false, Some("Name cannot be empty"))
    } else if (marks < 0 || marks > totalMarks) {
      (false, Some("Marks should be a positive integer not exceeding total possible marks"))
    } else {
      (true, None)
    }
  }

  private def getStudentInfoWithRetry: (String, Int, Int, Double, Char) = {
    var isValid = false
    var studentInfo: (String, Int, Int, Double, Char) = null

    while (!isValid) {
      try {
        studentInfo = getStudentInfo
        isValid = true
      } catch {
        case e: IllegalArgumentException =>
          println(e.getMessage)
      }
    }

    studentInfo
  }

  private def calculateGrade(percentage: Double): Char = {
    percentage match {
      case p if p >= 90 => 'A'
      case p if p >= 75 => 'B'
      case p if p >= 50 => 'C'
      case _ => 'D'
    }
  }

  private def printStudentRecord(record: (String, Int, Int, Double, Char)): Unit = {
    val (name, marks, totalMarks, percentage, grade) = record
    println(s"Student Name: $name")
    println(s"Marks Obtained: $marks / $totalMarks")
    println(s"Percentage: $percentage%")
    println(s"Grade: $grade")
  }

  def main(args: Array[String]): Unit = {
    val studentInfo = getStudentInfoWithRetry
    printStudentRecord(studentInfo)
  }
}
