<html>
<head>
<meta charset="UTF-8" />
<LINK rel="stylesheet" type="text/css" href="css/main.css"/>

<script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.bgiframe-2.1.1.js"></script>
<style type="text/css">
   #just_dialog div button { font-size: 62.5%; }
</style>

<script type="text/javascript"><!--

				jQuery(document).ready(function(){
					$('.technoAccordionDiv.head').click(function() {
						$(this).next().toggle('slow');
						return false;
					}).next().hide();
				});
				/*jQuery(document).ready(function(){
					$('.technoAccordionDiv.head').click(function() {
						$(this).next().toggle();
						return false;
					}).next().hide();
				});*/
				$(function() {
					$("#technoAccordionDiv").accordion();
					
				});
							
</script>


					<div id="technoAccordionDiv" >
						
					 	<h3 >
					 		<a href="#"><Font color="Black" size="2" face="Arial"><b>Tecnologias</b></Font></a>
					 	</h3>
						<div style="padding: 5px;">							
									
								<%
									com.adapit.portal.entidades.Technologies tecvet[] = new com.adapit.portal.entidades.Technologies[]{
										com.adapit.portal.entidades.Technologies.Desenvolvimento_Dirigido_por_Modelos,
										com.adapit.portal.entidades.Technologies.Linguagem_de_Programação_Java,
										com.adapit.portal.entidades.Technologies.Modelagem_de_Processos_de_Negócios
								};//com.adapit.portal.entidades.Technologies.values();
									for(int i=0; i < tecvet.length; i++){
										String value = tecvet[i].name().replace("_"," ");
										    //if(tecvet.length/2 == i) out.print("</td><td>");
								%>
									<div style=" width:100%; height:25px; background-color:<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>;
										cursor: hand;	cursor: pointer;"
										onclick="jqueryOpen('showFormacoes_.html?pageUrl=showFormacoes.html&tec=<%=tecvet[i].ordinal()%>','contentDiv'); return false;"
										onMouseOver="this.style.backgroundColor='#AAAAAA'" onMouseOut="this.style.backgroundColor='<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>'">
										<center>
										<a style="cursor: hand;	cursor: pointer;" 
											>
											<Font color="Black" size="2" face="Arial"><%=value%></Font>
										</a></center>
										
									</div>
								<%}%>							
									
						</div>
						
						
					</div>
					
							
</body>		
</html>	