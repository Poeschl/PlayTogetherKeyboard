export interface KeyStatistics {
  keys: KeyPress[];
}

export interface KeyPress {
  key: Key;
  presses: number;
}

export interface Key {
  code: string;
  key: string;
}
