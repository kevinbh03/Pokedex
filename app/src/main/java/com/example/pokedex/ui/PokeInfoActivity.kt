package com.example.pokedex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokedex.model.PokeInfoViewModel
import com.example.pokedex.databinding.ActivityPokeInfoBinding

class PokeInfoActivity : AppCompatActivity() {

    lateinit var viewModel: PokeInfoViewModel
    private lateinit var binding: ActivityPokeInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokeInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokeInfoViewModel::class.java]

        initUI()
    }

    private fun initUI() {
        val id = intent.extras?.get("id") as Int

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            val typeNames = pokemon.types.map { it.type.name }
            binding.nameTextView.text = pokemon.name
            binding.pokeTipo.text = "Tipo: ${typeNames.joinToString()}"
            val Evolucion = pokemon.types.map { it.type.name }
            binding.pokeEvolucion.text = "Evolución: ${Evolucion.joinToString()}"
            val Ataque = pokemon.moves.map { it.move.name }
            binding.pokeAtaques.text = "Ataques: ${Ataque.joinToString()}"
            val Habilidades = pokemon.abilities.map { it.ability.name }
            binding.pokeHabilidades.text = "Habilidades: ${Habilidades.joinToString()}"
            binding.pokeUbicacion.text = "Ubicacion: ${pokemon.locationAreaEncounters}"

            Glide.with(this).load(pokemon.sprites.frontDefault).into(binding.imageView)
        })
    }
}
