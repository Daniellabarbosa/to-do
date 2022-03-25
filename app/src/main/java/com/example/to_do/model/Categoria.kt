package com.example.to_do.model

data class Categoria (
    var id: Long,
    var descricao: String,
    var tarefas: List<Tarefa>
) {
}