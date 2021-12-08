<?php 

    error_reporting(E_ALL); 
    ini_set('display_errors',1); 

    include('dbcon.php');


    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


    if( (($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit'])) || $android )
    {

        // 안드로이드 코드의 postParameters 변수에 적어준 이름을 가지고 값을 전달 받습니다.

        $email=$_POST['email'];
        $que=$_POST['que'];

        if(empty($email)){
            $errMSG = "이메일을 입력하세요.";
        }
        else if(empty($que)){
            $errMSG = "QnA 입력하세요.";
        }

        if(!isset($errMSG)) // 이름과 나라 모두 입력이 되었다면 
        {
            try{
                // SQL문을 실행하여 데이터를 MySQL 서버의 person 테이블에 저장합니다. 
                $stmt = $con->prepare('insert into qna(que, email) values(:que, :email)');
                $stmt->bindParam(':email', $email);
                $stmt->bindParam(':que', $que);

                if($stmt->execute())
                {
                    $successMSG = "새로운 QnA를 추가했습니다.";
                }
                else
                {
                    $errMSG = "QnA 추가 에러";
                }

            } catch(PDOException $e) {
                die("Database error: " . $e->getMessage()); 
            }
        }

    }

?>


<?php 
    if (isset($errMSG)) echo $errMSG;
    if (isset($successMSG)) echo $successMSG;

	$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
   
    if( !$android )
    {
?>
    <html>
       <body>

            <form action="<?php $_PHP_SELF ?>" method="POST">
                Email: <input type = "text" name = "email" />
                QnA: <input type = "text" name = "que" />
                <input type = "submit" name = "submit" />
            </form>
       
       </body>
    </html>

<?php 
    }
?>