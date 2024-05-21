package com.estarly.petadoptionapp.ui

/**
 * Esta interfaz define las funciones que debe tener o puede tener una pantalla (Activity).
 */
interface ActivityStructure {
    /**
     * Inicializa las vistas de la pantalla.
     *
     * Esta función se utiliza para configurar e inicializar todos los elementos visuales (views) de la pantalla.
     * Se llama generalmente en el proceso de creación de la pantalla para asegurarse de que todas las vistas
     * están correctamente configuradas antes de interactuar con ellas.
     */
    fun initView()
    /**
     *
     * Inicializa los observadores de la pantalla.
     *
     * Esta función es opcional y se utiliza para configurar los observadores necesarios, como los LiveData del
     * ViewModel.
     */
    fun initObservers(){}
    /**
     *
     * Esta función recupera la información necesaria para mostrar en la Activity.
     *
     * Esta función es utilizada para obtener los datos que serán presentados en la Activity.
     * La función encapsula la lógica de recuperación de datos desde las fuentes correspondientes,
     * que pueden incluir bases de datos locales, servicios web, entre otro
     *
     */
    fun getData(){}
}