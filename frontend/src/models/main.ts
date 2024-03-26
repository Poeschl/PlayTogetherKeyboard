export interface KeyStatistics {
  keys: KeyPress[];
}

export interface KeyPress {
  char: string;
  presses: number;
}

export interface Key {
  char: string;
}
