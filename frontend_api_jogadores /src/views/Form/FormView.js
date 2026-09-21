import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import api from "../../services/api";

export default {
  name: "FormView",
  setup() {
    const router = useRouter();
    const route = useRoute();
    const idEdicao = route.params.id;

    const jogador = ref({
      nome: "",
      posicao: "",
      clube: "",
      idade: null,
      quantidadeGols: 0,
      quantidadePartidas: 0,
      ativo: true,
    });

    const carregarJogador = async () => {
      if (idEdicao) {
        try {
          const resposta = await api.get(`/jogadores/${idEdicao}`);
          jogador.value = resposta.data;
        } catch (erro) {
          alert("Erro ao carregar dados do jogador");
          router.push("/");
        }
      }
    };

    const salvarJogador = async () => {
      try {
        if (idEdicao) {
          await api.put(`/jogadores/${idEdicao}`, jogador.value);
        } else {
          await api.post("/jogadores", jogador.value);
        }
        router.push("/");
      } catch (erro) {
        alert("Erro ao salvar jogador");
        console.error(erro);
      }
    };

    onMounted(() => {
      carregarJogador();
    });

    return {
      jogador,
      idEdicao,
      salvarJogador,
    };
  },
};
