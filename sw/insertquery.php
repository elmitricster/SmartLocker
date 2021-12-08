<?php 

    error_reporting(E_ALL); 
    ini_set('display_errors',1); 

    include('dbcon.php');


    if( ($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit']))
    {

        $s_day=$_POST['s_day'];
        $l_day=$_POST['l_day'];
        $l_num=$_POST['l_num'];
        $b_num=$_POST['b_num'];
        $u_id=$_POST['u_id'];

        if(!isset($errMSG))
        {
            try{
                $stmt = $con->prepare('INSERT INTO reservation(s_day, l_day, l_num, b_num, u_id) VALUES(:s_day, :l_day,:l_num,:b_num,:u_id)');
                $stmt->bindParam(':s_day', $s_day);
                $stmt->bindParam(':l_day', $l_day);
                $stmt->bindParam(':l_num', $l_num);
                $stmt->bindParam(':b_num', $b_num);
                $stmt->bindParam(':u_id', $u_id);

                if($stmt->execute())
                {
                    $successMSG = "새로운 사용자를 추가했습니다.";
                }
                else
                {
                    $errMSG = "사용자 추가 에러";
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
        <?php 
        if (isset($errMSG)) echo $errMSG;
        if (isset($successMSG)) echo $successMSG;
        ?>
        
        <form action="<?php $_PHP_SELF ?>" method="POST">
            s_day: <input type = "text" name = "s_day" />
            l_day: <input type = "text" name = "l_day" />
            l_num: <input type = "text" name = "l_num" />
            b_num: <input type = "text" name = "b_num" />
            u_id: <input type = "text" name = "u_id" />
            <input type = "submit" name = "submit" />
        </form>
   
   </body>
</html>

<?php 
    }
?>