<?php  
error_reporting(E_ALL); 
ini_set('display_errors',1); 

include('dbcon.php');



//POST 값을 읽어온다.
$u_id=isset($_POST['u_id']) ? $_POST['u_id'] : '';
$crday = isset($_POST['crday']) ? $_POST['crday'] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if ($u_id != "" ){ 

    $sql="select s_day, l_day from reservation where u_id='$u_id'and s_day > $crday ";
    $stmt = $con->prepare($sql);
    $stmt->execute();
    if ($stmt->rowCount() == 0){

        echo "'";
        echo $u_id,", ",crday;
        echo "'은 찾을 수 없습니다.";
    }
	else{

   		$data = array(); 

        while($row=$stmt->fetch(PDO::FETCH_ASSOC)){

        	extract($row);

            array_push($data, 
                array(
                's_day'=>$row["s_day"],
                'l_day'=>$row["l_day"]
            ));
        }


        if (!$android) {
            echo "<pre>"; 
            print_r($data); 
            echo '</pre>';
        }else
        {
            header('Content-Type: application/json; charset=utf8');
            $json = json_encode(array("Locker"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
            echo $json;
        }
    }
}
else {
    echo "검색할 u_id를 입력하세요 ";
}

?>



<?php

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if (!$android){
?>

<html>
   <body>
   
      <form action="<?php $_PHP_SELF ?>" method="POST">
         유저아이디: <input type = "text" name = "u_id" />
         현재날짜: <input type = "text" name = "crday" />
         <input type = "submit" />
      </form>
   
   </body>
</html>
<?php
}

   
?>