package actions.global;

import actions.global.globalactions.AddAnnotationGlobalAction;
import actions.global.globalactions.AddDataDisplayBoxGlobalAction;
import actions.global.globalactions.AddHaskellComponentGlobalAction;
import actions.global.globalactions.AddHaskellEvaluatorComponentGlobalAction;
import actions.global.globalactions.AddInstanceMethodGlobalAction;
import actions.global.globalactions.AddInstanceOperationGlobalAction;
import actions.global.globalactions.AddLazyJavaClassGlobalAction;
import actions.global.globalactions.AddLazyJavaConstructorGlobalAction;
import actions.global.globalactions.AddLazyJavaFieldsComponentGlobalAction;
import actions.global.globalactions.AddLazyJavaMethodComponentGlobalAction;
import actions.global.globalactions.AddTextBoxGlobalAction;
import actions.global.globalactions.ConstructDataLineSegmentGlobalAction;
import actions.global.globalactions.ConstructLineSegmentGlobalAction;
import actions.global.globalactions.EditAnnotationSizeGlobalAction;
import actions.global.globalactions.EditRedoGlobalAction;
import actions.global.globalactions.EditUndoGlobalAction;
import actions.global.globalactions.ExecuteInstanceConstructorGlobalAction;
import actions.global.globalactions.ExecuteInstanceMethodGlobalAction;
import actions.global.globalactions.ExecuteScriptGlobalAction;
import actions.global.globalactions.FileOpenGlobalAction;
import actions.global.globalactions.FileSaveAsGlobalAction;
import actions.global.globalactions.GeneratePolygonSourceJavaGlobalAction;
import actions.global.globalactions.NameGlobalAction;
import actions.global.globalactions.RemoveAnnotationGlobalAction;
import actions.global.globalactions.UpdateDataInputBoxGlobalAction;

public enum ActionName {
	ADD_ANNOTATION_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new AddAnnotationGlobalAction();
		}
	},
	ADD_DATA_DISPLAY_BOX {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new AddDataDisplayBoxGlobalAction();
		}
	},
	ADD_HASKELL_EXPRESSION_COMPONENT {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new AddHaskellComponentGlobalAction();
		}
	},
	ADD_HASKELL_EVALUATOR_COMPONENT {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new AddHaskellEvaluatorComponentGlobalAction();
		}
	},
	ADD_INSTANCE_METHOD_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new AddInstanceMethodGlobalAction();
		}
	},
	ADD_INSTANCE_OPERATION_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new AddInstanceOperationGlobalAction();
		}
	},
	ADD_LAZY_JAVA_CLASS_ACTION {

		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new AddLazyJavaClassGlobalAction();
		}
	},
	ADD_LAZY_JAVA_CONSTRUCTOR_ACTION {

		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new AddLazyJavaConstructorGlobalAction();
		}
	},
	ADD_LAZY_JAVA_FIELDS_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new AddLazyJavaFieldsComponentGlobalAction();
		}
	},
	ADD_LAZY_JAVA_METHOD_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new AddLazyJavaMethodComponentGlobalAction();
		}
	},
	ADD_TEXT_BOX_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new AddTextBoxGlobalAction();
		}
	},
	CONSTRUCT_DATA_LINE_SEGMENT_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new ConstructDataLineSegmentGlobalAction();
		}
	},
	CONSTRUCT_LINE_SEGMENT_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new ConstructLineSegmentGlobalAction();
		}
	},
	EDIT_ANNOTATION_SIZE_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new EditAnnotationSizeGlobalAction();
		}
	},
	UNDO_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new EditUndoGlobalAction();
		}
	},
	REDO_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new EditRedoGlobalAction();
		}
	},
	EXECUTE_SCRIPT_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new ExecuteScriptGlobalAction();
		}
	},
	EXECUTE_INSTANCE_CONSTRUCTOR_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new ExecuteInstanceConstructorGlobalAction();
		}
	},
	EXECUTE_INSTANCE_METHOD_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new ExecuteInstanceMethodGlobalAction();
		}
	},
	FILE_OPEN_ACTION {
		@Override
		public GlobalPaintAction<FileOpenGlobalAction> getAssociatedAction() {
			return new FileOpenGlobalAction();
		}
	},
	FILE_SAVE_AS_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new FileSaveAsGlobalAction();
		}
	},
	GENERATE_POLYGON_SOURCE_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new GeneratePolygonSourceJavaGlobalAction();
		}
	},
	UPDATE_DATA_INPUT_BOX_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new UpdateDataInputBoxGlobalAction();
		}
	},
	REMOVE_ANNOTATION_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new RemoveAnnotationGlobalAction();
		}
	},
	FILE_OPEN_GLOBAL_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new FileOpenGlobalAction();
		}
	},
	FILE_SAVE_AS_GLOBAL_ACTION {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new FileSaveAsGlobalAction();
		}
	},
	NAME {
		@Override
		public GlobalPaintAction getAssociatedAction() {
			return new NameGlobalAction();
		}
	}

	;

	public abstract GlobalPaintAction getAssociatedAction();

}
