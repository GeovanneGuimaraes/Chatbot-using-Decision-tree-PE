# Hortin

O projeto tem como objetivo levar a produção excedente oriundas de agricultura familiar a consumidores que buscam alimentos orgânicos e que fomentem a sustentabilidade e um impacto socioeconômico positivo para as famílias produtoras.
Um ponto forte deste projeto é a facilidade que os produtores têm de cadastrar seus produtos, sendo intuitivo seu uso, tanto para o produtor como o comprador, de maneira ser mais inclusivo.
Para o produtor haverá um controle de estoque dele, além de apresentar quanto ele já recebeu até aquele momento, o ajudando com o controle e atualização dos itens a venda.
Ao cliente, haverá a listagem dos produtos disponíveis para a compra e a quantidade dos mesmos.

# Ferramentas Utilizadas
 FRONTEND:
  JavaScript
 BACKEND:
  JAVA (Framework SpringBoot)
 Banco de Dados:
  H2

# Como Baixar
Clonar o projeto do github.

# Como utilizar
Inicializar a aplicação spring boot e o front. Criar novo usuário ou fazer login. Como vendedor será apresentado na tela seus produtos existentes, é possível criar novo produto, caso o objetivo seja editar, selecione o produto desejado e clique em editar, insira as informações atualizadas e salve.
Como comprador todos os produtos são apresentados na tela, selecione os produtos desejados e adicione ao carrinho. Confira o carrinho, faça alterações desejadas, confira o total e confirme sua compra.

# Acesso ao Banco de dados H2 da aplicação
Para acessar a base de dados H2 vc deve estar com a API HORTIN rodando em sua máquina. Sendo assim você acessará o link  http://localhost:8080/h2-console, e na JBDC URL coloca o texto “jdbc:h2:mem:HORTIN” e o usuário “sa" e clique em Connect. Após isso verá algumas tabelas em que uma delas é a de PRODUTO, clicando nela você pode fazer selects na linguagem sql.
