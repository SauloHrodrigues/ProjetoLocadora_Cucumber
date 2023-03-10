# language: pt
Funcionalidade: Gerenciar locadora
	Como usuário
	Eu quero cadastrar alugueis de filmes
	Para controlar preço e datas de entrega 
	
Cenário: Deve alugar um filme com sucesso
	Dado um filme
	|estoque | 2|
	|preco   | 3|
	|tipo    |comum|
	Quando alugar
	Então o preço do aluguel será R$ 3
	#E a data de entrega será em 1 dia
	E a data de entrega sera em 1 dia 
	E o estoque do filme será 1 unidade
	
Cenário: Não deve alugar filme sem estoque
	Dado um filme com estoque de 0 unidades
	Quando alugar
	Então não será possivel por falta de estoque
	E o estoque do filme será 0 unidade
	
#Scenario outline
Esquema do Cenário: Deve dar condições conforme tipo do aluguel
	Dado um filme com estoque de 2 unidades
	E o preço do aluguél seja <preco>
	E que o tipo do aluguel seja <tipo>
	Quando alugar
	Então o preço do aluguel será R$<valor>
	E a data de entrega sera em <qtdeDias> dias
	E a pontuação recebida será de <qtdePontos> pontos 

#Examples	
Exemplos:
	|preco|tipo     | valor| qtdeDias| qtdePontos|
	| 4   |extendido|   8  |     3   |    2     |
	| 2   |comum    |   4  |     1   |    1     |
	| 10  |extendido|   20 |     3   |    2     |
	| 5   |semanal  |  15  |    7    |    3     |
#Cenário: Deve dar condições especiais para a categoria estendida
#	Dado um filme com estoque de 2 unidades
#	E o preço do aluguél seja 4
#	E que o tipo do aluguel seja extendido
#	Quando alugar
#	Então o preço do aluguel será R$8
#	E a data de entrega sera em 3 dias
#	E a pontuação recebida será de 2 pontos 
#	
#Cenário: Deve alugar para categoria comum
#	Dado um filme com estoque de 2 unidades
#	E o preço do aluguél seja 4
#	E que o tipo do aluguel seja comum
#	Quando alugar
#	Então o preço do aluguel será R$4
#	E a data de entrega sera em 1 dia
#	E a pontuação recebida será de 1 pontos 
	