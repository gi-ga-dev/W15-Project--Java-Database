package it.projects.catalogue;

public enum Periodicity {
	WEEKLY {
		@Override
		public String toString() {
			return "Weekly";
		}
	},
	MONTHLY {
		@Override
		public String toString() {
			return "Monthly";
		}
	},
	SEMESTRAL {
		@Override
		public String toString() {
			return "Semestral";
		}
	}

}
