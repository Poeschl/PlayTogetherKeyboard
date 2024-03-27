export default function useKeySanitizer() {
  const sanitizeLabel = (keyLabel: string): string => {
    if (keyLabel == " ") {
      return "🚀";
    }
    return keyLabel;
  };

  return { sanitizeLabel };
}
