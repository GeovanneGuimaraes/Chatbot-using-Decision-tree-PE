$(document).ready(function() {
    document.getElementById("usuarioAtual").innerHTML = sessionStorage.getItem("nome_usuario");
    buildTableComprador();
    $('#tabelaComprador').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            tableComprador.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );
 
    $('#button').click( function () {
        tableComprador.row('.selected').remove().draw( false );
    } );

    window.tableCarrinho = $("#tabelaCarrinho").DataTable({
        paging: false,
        pagingType : "full",
        colReorder: true,
        searching : false,
        "processing" : true,
        "columns": [

            {"data": "produto", "className" : ''},
            {"data": "preco", "className" : ''},
            {"data": "quantidade", "className" : ''},
            {"data": "id", "className" : ''},
            {"data": "vendedor", "className" : ''}
        ],
        "columnDefs": [
            
            {
                "targets" : '_all',
                searchPanes:{show: true}
            }
        ],
        'select' : {"style" : "single"},
        "language": {
            "zeroRecords" : "Nenhum dado encontrado",
            "infoEmpty" : "Nenhum dado encontrado",
            "infoFiltered" : "(filtrado de um total de _MAX_ dados)",
            "lengthMenu" : "Mostrar _MENU_ dados por página",
            "search":         "Pesquisar:",
            "info":           "Exibindo _START_ a _END_ de _TOTAL_ produtos",
            "paginate": {
                "first":      "Primeiro",
                "last":       "Último",
                "next":       "Próximo",
                "previous":   "Anterior"
            },
            searchPanes : {
                clearMessage : 'Remover Filtros'
            }
        },
        stateSave : true,
        dom: 'Bftri'
    });


    $('#tabelaCarrinho').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            tableCarrinho.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );
 
    $('#button').click( function () {
        tableCarrinho.row('.selected').remove().draw( false );
    } );
   
});

function buildTableComprador(){

    window.tableComprador = $("#tabelaComprador").DataTable({
        paging: true,
        pagingType : "full",
        colReorder: true,
        searching : true,
        "processing" : true,
        "ajax" : {
            "url" : "http://localhost:8080/produto/",
            "type" : "GET",
            dataSrc: ''
        },
        "columns": [

            {"data": "nomeProduto", "className" : ''},
            {"data": "descricaoProduto", "className" : ''},
            {"data": "valorProduto", "className" : ''},
            {"data": "quantidadeProduto", "className" : ''},
            {"data": "vendedor.nome", "className" : ''},
            {"data": "id_produto", "className" : ''}
        ],
        "columnDefs": [
            
            {
                "targets" : '_all',
                searchPanes:{show: true}
            }
        ],
        'select' : {"style" : "single"},
        "language": {
            "zeroRecords" : "Nenhum dado encontrado",
            "infoEmpty" : "Nenhum dado encontrado",
            "infoFiltered" : "(filtrado de um total de _MAX_ dados)",
            "lengthMenu" : "Mostrar _MENU_ dados por página",
            "search":         "Pesquisar:",
            "info":           "Exibindo _START_ a _END_ de _TOTAL_ produtos",
            "paginate": {
                "first":      "Primeiro",
                "last":       "Último",
                "next":       "Próximo",
                "previous":   "Anterior"
            },
            searchPanes : {
                clearMessage : 'Remover Filtros'
            }
        },
        stateSave : true,
        dom: 'Bftrip'
    });
    var dt = $('#tabelaComprador').DataTable();
//hide the 5th column
dt.column(5).visible(false);

}

function adicionaNoCarrinho(){
    const carrinho = JSON.parse(localStorage.getItem('carrinho'))  || []
    var quantidade = parseInt(document.getElementById("pQuantidade").value);
    var selectedRow = tableComprador.row('.selected').data();
    console.log(carrinho);
    if (carrinho.filter(e => e.id === selectedRow.id_produto).length > 0) {
        console.log("sim");
    }
    else{
        const novoProduto = {
            "vendedor": selectedRow.vendedor.nome,
            "produto": selectedRow.nomeProduto,
            "preco": selectedRow.valorProduto,
            "quantidade": quantidade,
            "id": selectedRow.id_produto,
        }

        const tarefasAtualizadas = [...carrinho, novoProduto]
        
        console.log(tarefasAtualizadas)
        localStorage.setItem('carrinho', JSON.stringify(tarefasAtualizadas))
    }

}

function temSelecao(){
    if (! tableComprador.rows( '.selected' ).any() ){
        alert('Selecione uma linha para realizar esta ação.');;

    }
    else{
        $('#pQuantidade').val('');
        $('#modalQuantidade').modal('show');
    }

}

function verCarrinho(){
    var paragrafo = document.querySelector('#valor_total')
    paragrafo.innerHTML = ""
    const carrinho = JSON.parse(localStorage.getItem('carrinho'))  || []
    console.log(carrinho);
    if (carrinho.length == 0){
        alert('Carrinho vazio');
    }else{
        buildTableCarrinho();
        $('#modalCarrinho').modal('show');

    }
    carregaTotal()
}

function buildTableCarrinho(){
    const carrinho = JSON.parse(localStorage.getItem('carrinho'))  || []

    if ( $.fn.dataTable.isDataTable( '#tabelaCarrinho' ) ) {
        var dt = $('#tabelaCarrinho').DataTable();
    }
    else{
        var dt = $('#tabelaCarrinho').DataTable({     
            retrieve: true,
            paging: false});
    }
    dt.clear().draw()
//hide the 5th column
    dt.column(3).visible(false)
    console.log(carrinho)
    dt.rows.add(carrinho).draw()
}
function Comprar(){
    var url= 'http://localhost:8080/produto/Compra'
    if (confirm("Deseja comprar estes produtos?") == true) {
        const carrinho = localStorage.getItem('carrinho') || []
        $.ajax({
            method: "PUT",
            crossDomain: true,
            url : url,
            dataType : "json",
            contentType: "application/json; charset=utf-8",
            processData : false,
            data  :  carrinho,
            success : function (res){
                alert('Comprado com sucesso 😀');
            },
            error : function(res){
                console.log(res);
                alert('Algo de errado aconteceu😥 Verifique a quantidade do produto escolhido');
            }
        })
        localStorage.setItem('carrinho', JSON.stringify([]))
        document.location.reload(true)

    }
}

function carregaTotal(){
    var valor = 0
    const carrinho = JSON.parse(localStorage.getItem('carrinho'))  || []
    carrinho.forEach(produto => {
        valor += parseInt(produto.preco) * parseInt(produto.quantidade)
    });
    var paragrafo = document.querySelector('#valor_total')
    const total = document.createElement('p')
    total.innerHTML = "O TOTAL É: <strong> " + valor + "<strong> reais"
    paragrafo.appendChild(total)
}

function RemoverCarrinho(){
    if ( tableCarrinho.rows( '.selected' ).any() ){
        var selectedRow = tableCarrinho.row('.selected').data();
        var idproduto = (selectedRow.id);

        const carrinho = JSON.parse(localStorage.getItem('carrinho'))  || []
        var carrinho_filtrado = carrinho.filter(function(e) { return e.id !== idproduto })

        localStorage.setItem('carrinho', JSON.stringify(carrinho_filtrado))
    }
    else{
        alert('Selecione uma linha para continuar com a ação')
    }
}