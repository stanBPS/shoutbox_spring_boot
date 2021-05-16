$( document ).ready(function() {
	$("#msg_container").animate({ scrollTop: $(document).height() }, 1000);
  	$(".name").each(function(){
    	var name = $(this).html();
    	$(this).html(name.toUpperCase());
  	})
  	$(".date").each(function(){
    	var date =  $(this).html()
    	var newDate = new Date(date)
    	$(this).html(newDate.toLocaleString())
  	})
  	
  	$(".alert").on('click',function(){
  		$("input[name=message]").val($(this).data('id'))
      	$('.container_modal_report').css("display", "block")
	})
	
	$(".edit_button").on('click',function(){
		//$(".action_modif").attr("action", "/admin/gestion_message/edit/"+$(this).data('id'));
		$("input[name=idMessage]").val($(this).data('id'))
		$("input[name=idPseudo]").val($(this).data('idpseudo'))
  		$('.container_modal_edit').css("display", "block")
	})
    
	$(".close").on('click',function(){
	  $('.container_modal_report').css("display", "none")
	  $('.container_modal_edit').css("display", "none")
	}) 

	var modal = document.getElementsByClassName("container_modal_report");
	var modalEdit = document.getElementsByClassName("container_modal_edit");
	
	window.onclick = function(event) {
	  console.log(event.target);
	  if (event.target == modal || event.target == modalEdit) {
	    $('.container_modal_report').css("display", "none")
	    $('.container_modal_edit').css("display", "none")
	  }
	}
	
	 $("#search_bar").hide();
	
	$("#button_search").click(function(){
	  $("#search_bar").toggle( "slide",{ direction: "right" } );
	}); 
	 
	$(".input_search_text").on('keyup search',function(){
	  $(".msg p").each(function(){
	    if($(this).text().toUpperCase().indexOf($(".input_search_text").val().toUpperCase()) != -1 || $(".input_search_text").val() == ''){
	      $(this).parents(".message_li").show();
	    }else{
	     $(this).parents(".message_li").hide();
	    }
	  })
	  
	})
	
	$(".input_search_user").on('keyup search',function(){
	  $(".name").each(function(){
	    if($(this).text().toUpperCase().indexOf($(".input_search_user").val().toUpperCase()) != -1 || $(".input_search_user").val() == ''){
	      $(this).parents(".message_li").show();
	    }else{
	      $(this).parents(".message_li").hide();
	    }
	  })
	  
	})
	
	var imageD = '[image]'
	var imageF = '[/image]'
	var link = [];
	var link2 = [];
	var i =0;
	
	$(".msg p, .message_container p, .message_report_container.messageSubject").each(function(){
	  link[i] =  $(this).text().substring($(this).text().lastIndexOf(imageD) , $(this).text().lastIndexOf(imageF)+8)
	  link2[i] =  $(this).text().substring($(this).text().lastIndexOf(imageD)+7 , $(this).text().lastIndexOf(imageF))
	  var lien = $(this).text();
	  if(link[i].trim() != "" && (link2[i].endsWith('.jpg') || link2[i].endsWith('.jpeg') || link2[i].endsWith('.gif') || link2[i].endsWith('.png') )){
	    lien = lien.replace(link[i], '<img src="'+link2[i]+'" alt="">')
	  }
	  if(link[i].trim() === "" || (!link2[i].endsWith('.jpg') || !link2[i].endsWith('.jpeg') || !link2[i].endsWith('.gif') || !link2[i].endsWith('.png')))  {
	    lien = lien.replace(link[i], link2[i])
	  }
	  $(this).text('')
	  $(this).prepend(lien);
	    i++;
	});
	
	
	$("#image_add").on('click',function(){
	  $("#msg_to_send").val($("#msg_to_send").val()+' [image]lien de votre image...[/image]')
	})
});