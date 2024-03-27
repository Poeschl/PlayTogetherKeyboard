export default function useKeySanitizer() {
  const sanitizeLabel = (keyLabel: string): string => {
    if (keyLabel == " ") {
      return "🚀";
    } else if (keyLabel == "ArrowUp") {
      return "⬆";
    } else if (keyLabel == "ArrowDown") {
      return "⬇";
    } else if (keyLabel == "ArrowLeft") {
      return "⬅";
    } else if (keyLabel == "ArrowRight") {
      return "➡";
    }
    return keyLabel;
  };

  return { sanitizeLabel };
}
