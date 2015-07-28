<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<!--
	Transit by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Elements - Transit by TEMPLATED</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
		<script src="js/jquery.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-xlarge.css" />
		</noscript>
		
		<script>
		
		function enableTabsInTextArea(){
			var textareas = document.getElementsByTagName('textarea');
			var count = textareas.length;
			for(var i=0;i<count;i++){
			    textareas[i].onkeydown = function(e){
			        if(e.keyCode==9 || e.which==9){
			            e.preventDefault();
			            var s = this.selectionStart;
			            this.value = this.value.substring(0,this.selectionStart) + "\t" + this.value.substring(this.selectionEnd);
			            this.selectionEnd = s+1; 
			        }
			    };
			}
		}
		
		function updateMockupsTable(jsonResponse) {
            $.each(jsonResponse.mockupsMap, function(i, item) {
            	
            	var cb_id = "cb"+i;
            	var cb = "<input type='checkbox' id='"+cb_id+"' name='human' ><label for='"+cb_id+"'></label>";
            	
            	var tr =	$('<tr>').append(
	                        	$('<td>').text(i),
	                        	$('<td>').text(item.method_mock),
	                        	$('<td>').text(item.url_mock),
	                        	$('<td>').append(cb)
                    		); 
            	$('#tabla_mockups').append(tr);

            });
        }
		
		$(document).ready(function() {
			
			
			//LOAD MOCKUPS ///////////////////////////
			$("#tabla_mockups").find("tr:gt(0)").remove();
			
			var data_in = 	{ 
				actionType 	: "load_mockups",
				project_id	: 1
			};

			var function_response = function(jsonResponse) {
			
				$(updateMockupsTable(jsonResponse));
				var footer = "<tfoot><tr><td colspan='3'></td><td><input type='button' value='Delete' /></td></tr></tfoot>";
				$('#tabla_mockups').append(footer);
			};
			
			$.getJSON(
				'proxyserverAction',//the name of the action defined in struts.xml 
				data_in, 
				function_response
			);
			
			///////////////////////////////////////////////////////
			
			enableTabsInTextArea();
			
			//CREATE MOCKUPS ////////////////////////////////////
			$('#create_mockup').click(function(event) {
				
				var resource_name = $('#resourcename').val();
				var mockup_data = $('textarea#mockup_data').val();
				var http_method = $("#http_method").find('option:selected').text();
				var data_in = 	{ 
									resource	: resource_name,
									method		: http_method,
									actionType 	: "create_mockup",
									mockup 		: mockup_data
								};
				var function_response = function(jsonResponse) {
					$("#tabla_mockups").find("tr:gt(0)").remove();
					$(updateMockupsTable(jsonResponse));
					var footer = "<tfoot><tr><td colspan='3'></td><td><input type='button' value='Delete' /></td></tr></tfoot>";
					$('#tabla_mockups').append(footer);
				};
				
				$.getJSON(
					'proxyserverAction',//the name of the action defined in struts.xml 
					data_in, 
					function_response
				);
			
			});
			
			
			//UPDATE MOCKUPS ////////////////////////////////////
			$('#update_mockup').click(function(event) {
				
				var server_name = $('#servername').val();
				var data_in = 	{ 
									actionType 	: "update_mockup",
									servername	: server_name
								};
				var function_response = function(jsonResponse) {
				};
				
				$.getJSON(
					'proxyserverAction',//the name of the action defined in struts.xml 
					data_in, 
					function_response
				);
			
			});
			//////////////////////////////////
		});
		</script>
	</head>
	<body>

		<!-- Header -->
			<header id="header">
				<h1><a href="index.html">Transit</a></h1>
				<nav id="nav">
					<ul>
						<li><a href="index.html">Home</a></li>
						<li><a href="elements.jsp">Proxy</a></li>
						<li><a href="#" class="button special">Sign Up</a></li>
					</ul>
				</nav>
			</header>

		<!-- Main -->
			<section id="main" class="wrapper">
				<div class="container">

					<header class="major">
						<h2>Create your own mockups</h2>
						<p>Use mockups for testing</p>
					</header>

					
						<section>
							<h3>Default URI</h3>
							<form method="post" action="#">
								<div class="row uniform 50%">
									<div class="12u$">
										<input type="text" name="servername" id="servername" value="http://www.sonrie.com/apps/movil/" />
									</div>
									<div class="12u$">
										<ul class="actions">
											<li><input type="button" id="update_mockup" value="Update URI" class="special" /></li>
										</ul>
									</div>
								</div>
							</form>
						</section>


						<!-- Table -->
						<section>							
							<header>
								<h3>Current mockups</h3>
								<p>Add or delete your json mockups</p>
							</header>

							<div class="table-wrapper">
								<table class="alt" id="tabla_mockups">
									<thead>
										<tr>
											<th>ID</th>
											<th>Method</th>
											<th>Mock</th>
											<th>Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>Item 1</td>
											<td>Ante turpis integer aliquet porttitor.</td>
											<td>
												<div class="6u$ 12u$(3)">
												<input type="checkbox" id="cb1" name="human" >
												<label for="cb1"></label>
												</div>
											</td>
										</tr>
										<tr>
											<td>Item 2</td>
											<td>http://localhost:8080/proxy/api/any/app_perfil.php</td>
											<td>
												<div class="6u$ 12u$(3)">
												<input type="checkbox" id="cb2" name="human" >
												<label for="cb2"></label>
												</div>
											</td>
										</tr>
										<tr>
											<td>Item 3</td>
											<td> Morbi faucibus arcu accumsan lorem.</td>
											<td>
												<div class="6u$ 12u$(3)">
												<input type="checkbox" id="cb3" name="human" >
												<label for="cb3"></label>
												</div>
											</td>
										</tr>
										<tr>
											<td>Item 4</td>
											<td>Vitae integer tempus condimentum.</td>
											<td>
												<div class="6u$ 12u$(3)">
												<input type="checkbox" id="cb4" name="human" >
												<label for="cb4"></label>
												</div>
											</td>
										</tr>
										
									</tbody>
									<tfoot>
										<tr>
											<td colspan="2"></td>
											<td><input type="button" value="Delete" /></td>
										</tr>
									</tfoot>
									
								</table>
							</div>
						</section>

						<!-- Form -->
						<section>
							<h3>Add new JSON mockup</h3>
							<form method="post" action="#">
								<div class="row uniform 50%">
									<div class="6u 12u$(4)">
										<input type="text" name="resourcename" id="resourcename" value="" placeholder="Name" />
									</div>
									<div class="6u$ 12u$(4)">
										<div class="select-wrapper">
											<select name="http_method" id="http_method">
												<option value="NONE">- select method -</option>
												<option value="GET">GET</option>
												<option value="POST">POST</option>
												<option value="PUT">PUT</option>
												<option value="DELETE">DELETE</option>
											</select>
										</div>
									</div>
									
									<div class="12u$">
										<textarea name="mockup_data" id="mockup_data" placeholder="Enter your JSON mockup" rows="6"></textarea>
									</div>
									<div class="12u$">
										<ul class="actions">
											<li><input type="button" value="Create mockup" class="special" id="create_mockup" name="create_mockup"/></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</div>
								</div>
							</form>
						</section>
	</body>
</html>