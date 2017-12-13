$(document).ready(function() {
    $('#example').DataTable();
    $('#button-add-jadwal').click(function(){
    	var counter = parseInt($('#counter').text())+1;
    	$('#add-jadwal').append("<br/><h5>Jadwal "+counter+"</h5><hr/><div class='form-group'><label>Hari</label><input type='text' name='nomor_term' class='form-control'/></div><div class='row'><div class='col-6'><label>Jam Mulai</label><input type='time' name='nomor_term' class='form-control'/></div><div class='col-6'><label>Jam Selesai</label><input type='time' name='nomor_term' class='form-control'/></div></div>");
    	$('#counter').text(counter);
    	return false;
    });
} );