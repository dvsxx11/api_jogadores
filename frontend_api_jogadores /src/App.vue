<script setup>
import { RouterLink, RouterView } from "vue-router";
</script>

<template>
  <header>
    <nav>
      <h2>Painel de Jogadores</h2>
      <div class="links">
        <RouterLink to="/">Lista de Jogadores</RouterLink>
        <RouterLink to="/novo">Adicionar Jogador</RouterLink>
      </div>
    </nav>
  </header>

  <RouterView />
</template>

<style scoped>
header {
  background-color: #1e293b;
  padding: 1rem 2rem;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

nav {
  max-width: 1000px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

h2 {
  font-size: 1.25rem;
  font-weight: 700;
  letter-spacing: -0.5px;
  color: #ffffff;
}

.links {
  display: flex;
  gap: 10px;
}

.links a {
  color: #94a3b8;
  text-decoration: none;
  font-weight: 600;
  font-size: 14px;
  padding: 8px 14px;
  border-radius: 6px;
  transition: all 0.2s ease;
}

.links a:hover {
  color: #ffffff;
  background-color: rgba(255, 255, 255, 0.08);
}

.links a.router-link-active {
  color: #ffffff;
  background-color: #10b981;
}
</style>
