import { ref, computed, onMounted } from "vue";
import api from "../../services/api";

export default {
  name: "HomeView",
  setup() {
    const jogadores = ref([]);
    const mensagemErro = ref("");
    const filtroStatus = ref("todos");
    const clubeSelecionado = ref("");
    const clubes = computed(() => {
      const nomes = new Map();
      jogadores.value.forEach((jogador) => {
        const nome = jogador.clube?.trim();
        if (nome) nomes.set(nome.toLocaleLowerCase(), nome);
      });
      return [...nomes.entries()]
        .sort((a, b) => a[1].localeCompare(b[1], "pt-BR"))
        .map(([valor, nome]) => ({ valor, nome }));
    });
    const jogadoresAtivos = computed(() =>
      jogadores.value.filter((jogador) => jogador.ativo),
    );
    const jogadoresInativos = computed(() =>
      jogadores.value.filter((jogador) => !jogador.ativo),
    );
    const jogadoresFiltrados = computed(() => {
      if (filtroStatus.value === "ativos") return jogadoresAtivos.value;
      if (filtroStatus.value === "inativos") return jogadoresInativos.value;
      if (filtroStatus.value === "clubes") {
        return jogadores.value.filter(
          (jogador) =>
            jogador.clube?.trim().toLocaleLowerCase() ===
            clubeSelecionado.value,
        );
      }
      return jogadores.value;
    });
    const totalClubes = computed(() => clubes.value.length);

    const carregarJogadores = async () => {
      try {
        const resposta = await api.get("/jogadores");
        jogadores.value = resposta.data;
      } catch (erro) {
        mensagemErro.value = "Erro ao carregar os dados. A API está rodando?";
        console.error(erro);
      }
    };

    const apagarJogador = async (id) => {
      if (confirm("Tem certeza que deseja apagar este jogador?")) {
        try {
          await api.delete(`/jogadores/${id}`);
          carregarJogadores();
        } catch (erro) {
          alert("Erro ao apagar jogador");
        }
      }
    };

    onMounted(() => {
      carregarJogadores();
    });

    return {
      jogadores,
      filtroStatus,
      clubeSelecionado,
      clubes,
      jogadoresAtivos,
      jogadoresInativos,
      jogadoresFiltrados,
      totalClubes,
      mensagemErro,
      apagarJogador,
    };
  },
};
