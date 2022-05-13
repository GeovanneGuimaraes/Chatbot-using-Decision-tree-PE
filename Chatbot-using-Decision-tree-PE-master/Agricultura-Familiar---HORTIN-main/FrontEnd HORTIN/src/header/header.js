    $(document).ready(function() {
        document.getElementById("usuarioAtual").innerHTML = sessionStorage.getItem("nome_usuario");
        buildTableVendedor();

        $('#tabelaVendedor').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        } );

        $('#button').click( function () {
            table.row('.selected').remove().draw( false );
        } );


    });

    function buildTableVendedor(){
        var vendedor = sessionStorage.getItem("id_usuario");


        window.table = $("#tabelaVendedor").DataTable({
            paging: true,
            pagingType : "full",
            colReorder: true,
            searching : true,
            "processing" : true,
            "ajax" : {
                "url" : "http://localhost:8080/produto/vendedor/"+ vendedor.toString(),
                "type" : "GET",
                dataSrc: ''
            },
            "columns": [
                {"data": "nomeProduto", "className" : ''},
                {"data": "descricaoProduto", "className" : ''},
                {"data": "valorProduto", "className" : ''},
                {"data": "quantidadeProduto", "className" : ''},
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
        var dt = $('#tabelaVendedor').DataTable();
    //hide the fourth column
        dt.column(4).visible(false);

    }



    function novoProduto(){
        var vendedor = sessionStorage.getItem("id_usuario")
        var data = new FormData($("#cadastroProduto").get(0));
        var value = JSON.stringify(Object.fromEntries(data.entries()));
        var url= 'http://localhost:8080/produto/vendedor/' + vendedor.toString()
        console.log(value)
        $.ajax({
            method: "POST",
            crossDomain: true,
            url : url,
            dataType : "json",
            contentType: "application/json; charset=utf-8",
            processData : false,
            data  :  value,
            success : function (res){
                $("#modalNovoProduto").modal('hide')
                alert('Ação executada com sucesso 😀', 'success');
            },
            error : function(res){
                console.log(res);
                alert('Algo de errado aconteceu 😥', 'error');
            }
        })
        $('#pNome').val('');
        $('#pDesc').val('');
        $('#pValor').val('');
        $('#pQuantidade').val('');
        document.location.reload(true)
    }


    function editarProduto(){

        if ( table.rows( '.selected' ).any() ){
            var selectedRow = table.row('.selected').data();

            $("#eNome").val(selectedRow.nomeProduto);
            $("#eDesc").val(selectedRow.descricaoProduto);
            $("#eValor").val(selectedRow.valorProduto);
            $("#eQuantidade").val(selectedRow.quantidadeProduto);




            $('#modalEditarProduto').modal('show');
        }

        else{
            alert('Selecione uma linha para realizar esta ação.');
        }
    }

    function salvarEdicao(){
        let text;
        if (confirm("Deseja salvar alterações?") == true) {
            var selectedRow = table.row('.selected').data();
            var idproduto = (selectedRow.id_produto);

            var data = new FormData($("#editarProduto").get(0));
            var value = JSON.stringify(Object.fromEntries(data.entries()));
            var url= 'http://localhost:8080/produto/' + idproduto.toString()
            console.log(value)
            $.ajax({
                method: "PUT",
                crossDomain: true,
                url : url,
                dataType : "json",
                contentType: "application/json; charset=utf-8",
                processData : false,
                data  :  value,
                success : function (res){
                    $("#modalEditarProduto").modal('hide')
                    alert('Editado com sucesso 😀');
                },
                error : function(res){
                    console.log(res);
                    alert('Algo de errado aconteceu 😥');
                }
            })
            $('#eNome').val('');
            $('#eDesc').val('');
            $('#eValor').val('');
            $('#eQuantidade').val('');

            document.location.reload(true)
        } 
        else {

        }

    }

    function deletarProduto(){
        if ( table.rows( '.selected' ).any() ){
            if (confirm("Deseja salvar alterações?") == true) {
                var selectedRow = table.row('.selected').data();
                var idproduto = (selectedRow.id_produto);
                var url= 'http://localhost:8080/produto/' + idproduto.toString()
                $.ajax({
                    method: "DELETE",
                    crossDomain: true,
                    url : url,
                    success : function (res){
                        alert('Deletado com sucesso 😀');
                    },
                    error : function(res){
                        console.log(res);
                        alert('Algo de errado aconteceu 😥');
                    }
            })
            document.location.reload(true)
            }

            else {

            }


        }

        else{
            alert('Selecione uma linha para realizar esta ação.');
        }
    }
