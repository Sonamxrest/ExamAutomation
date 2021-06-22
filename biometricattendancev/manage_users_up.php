<div class ="row">
<div class="col-lg-12">
<div class="table-responsive-sm " style="height:200px;opacity:0.8;"> 
  <table class="table">
    <thead class="table-primary">
      <tr>
        <th>Finger .ID</th>
        <th>Name</th>
      
        <th>S.No</th>
      
        <th>Status</th>
      </tr>
    </thead>
    <tbody class="table-secondary">
    <?php
      //Connect to database
      require'connectDB.php';

        $sql = "SELECT * FROM users WHERE del_fingerid=0";
        $result = mysqli_stmt_init($conn);
        if (!mysqli_stmt_prepare($result, $sql)) {
            echo '<p class="error">SQL Error</p>';
        }
        else{
            mysqli_stmt_execute($result);
            $resultl = mysqli_stmt_get_result($result);
          if (mysqli_num_rows($resultl) > 0){
              while ($row = mysqli_fetch_assoc($resultl)){
      ?>
                  <TR>
                  	<TD><?php  
                    		if ($row['fingerprint_select'] == 1) {
                    			echo "<span><i class='glyphicon glyphicon-ok' title='The selected UID'></i></span>";
                    		}
                        $fingerid = $row['fingerprint_id'];
                        $device_uid = $row['device_uid'];
                    	?>
                    	<form>
                    <button type="button" class="select_btn" data-id="<?php echo $fingerid;?>" name="<?php echo $device_uid;?>" title="select this UID"><?php echo $fingerid;?></button>
                    	</form>
                    </TD>
                  <TD><?php echo $row['username'];?></TD>
                  
                  <TD><?php echo $row['serialnumber'];?></TD>
           
                  <TD><?php echo ($row['add_fingerid'] == "0") ? "Added" : "Not Registered"?></TD>
                  </TR>
    <?php
            }   
        }
      }
    ?>
	


    </tbody>
  </table>
  </div>
</div>

</div>