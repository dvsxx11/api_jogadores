<script setup>
import { RouterLink, RouterView } from "vue-router";
</script>
<template>
  <div class="app-shell">
    <header class="site-header">
      <nav class="nav-inner" aria-label="Navegação principal">
        <RouterLink to="/" class="brand"
          ><span class="brand-mark">S</span
          ><span>Soccer<em>Link</em></span></RouterLink
        >
        <div class="nav-links">
          <RouterLink to="/" exact-active-class="is-active"
            >Visão geral</RouterLink
          ><RouterLink to="/novo" class="nav-add">+ Novo jogador</RouterLink>
        </div>
      </nav>
    </header>
    <main><RouterView /></main>
  </div>
</template>
