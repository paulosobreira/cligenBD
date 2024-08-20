

 **[CliGenBD](https://sowbreira-26fe1.firebaseapp.com/cligenbd/cligenBD.zip)**

## Cliente Genérico de Banco de Dados.

![](https://sowbreira-26fe1.firebaseapp.com/cligenbd/pic1.jpg)

CliGenBD é um simples programa para fornecer acesso a visualização e manipulação de SQL em diversos SGBDs.

![](https://sowbreira-26fe1.firebaseapp.com/cligenbd/pic2.jpg)

No menu principal é possível iniciar uma conecção usando os drivers e urls previamente cadastrados no arquivo databases.xml ou simplesmente digita-las.

Para acesso a bancos de dados jdbc que não estejam no arquivo é nescessario copiar o .jar do driver do banco para pasta libs e editar o arquivo run.bat para adcionar o novo jar no classpath.

 ![](https://sowbreira-26fe1.firebaseapp.com/cligenbd/pic3.jpg)

Arquivo databases.xml que pode ser editado para alteração dos padrões e/ou adiconar mais drivers.

![](https://sowbreira-26fe1.firebaseapp.com/cligenbd/pic4.jpg)

Após conectar ao BD com sucesso é demostrado o nome do provedor e versão juntamente com todas as tabelas que o usuário logado tem acesso, é possivel abrir a tabela selecionada ou executar uma cosulta avulsa.

![](https://sowbreira-26fe1.firebaseapp.com/cligenbd/pic5.jpg)

Uma tabela aberta significa apenas que o programa fez uma simples consulta mostrando o primeiro resgistro encontrado.

Qualquer Sql pode ser executado nesta janela. Ao clicar em campos o programa verifica o metadados da tabela para descobrir a tipagem nativa dos campos.

![](https://sowbreira-26fe1.firebaseapp.com/cligenbd/pic6.jpg)

Os botões contendo <Comando> geram templates SQL das ações. Um comando pode ser selecionado e excutado com o atalho F5

![](https://sowbreira-26fe1.firebaseapp.com/cligenbd/pic7.jpg)

Ao se selecionar um ou mais campos na tabela de resultados e clicar no template de Update o sql gerado conterá os campos selecionados.

O Resultado selecionado na tabela também pode ser exportado para o programa de planhilas.

![](https://sowbreira-26fe1.firebaseapp.com/cligenbd/pic8.jpg)

Para auxilar na adição de novos drivers, o programa possui um gerador de classpath bastando apenas informar o diretório onde estão as libs.

## Requerimentos

- Para executar o jogo é necessário Java 11
- Para rodar uma instância do jogo no Windows execute run.bat
- Para rodar uma instância do jogo no linux execute run.sh

## Informação técnica

- Construção Maven
    - mvn clean package


